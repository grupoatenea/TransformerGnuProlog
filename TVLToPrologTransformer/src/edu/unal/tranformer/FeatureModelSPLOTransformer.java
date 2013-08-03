package edu.unal.tranformer;

/* 
 * Conjunto de librerías importadas necesarias para el correcto funcionamiento de
 *
 *  la aplicación. 
 * 
 * */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.TransformerException;

import constraints.BooleanVariable;
import constraints.PropositionalFormula;
import edu.unal.DTO.TransformerInDTO;
import edu.unal.constants.ConstraintSymbolsConstant;
import edu.unal.constants.GNUPrologConstraintSymbolsConstant;
import edu.unal.constants.SWIPrologConstraintSymbolsConstant;
import edu.unal.constants.TransformerConstants;
import edu.unal.model.enums.SolverEditorType;
import edu.unal.utils.FileUtils;
import fm.FeatureGroup;
import fm.FeatureModel;
import fm.FeatureModelException;
import fm.FeatureTreeNode;
import fm.RootNode;
import fm.SolitaireFeature;
import fm.XMLFeatureModel;

/**
 * La Clase TransformationManager es la encargada de realizar las respectivas
 * transformaciones de los modelos de un formalismo base a GNUProlog-SWI Prolog
 * 
 * @author Juliana Jaramillo Echeverri, José Ignacio López Vélez
 * @version 1.7.0, 10/01/2011
 * @author Luisa Rincón
 * @Version 2.0 Abril/2013
 */
public class FeatureModelSPLOTransformer {

	private StringBuilder groups;
	private StringBuilder mandatories;
	private StringBuilder optional;
	private StringBuilder orRelations;
	private String allFeaturesString;
	private String allDomainString;
	private Long constraintCounter;

	// Los elementos comunes que son diferentes segun el editor
	private String header;
	private String endConstraintProgramFile;

	private List<String> variabilityElementList;
	private SolverEditorType prologEditorType;

	private FeatureModelTransformerRules transformerRules;

	private void init(TransformerInDTO inDTO) {

		prologEditorType = inDTO.getSolverEditorType();
		mandatories = new StringBuilder();
		optional = new StringBuilder();
		orRelations = new StringBuilder();
		groups = new StringBuilder();
		allFeaturesString = allDomainString = "";
		constraintCounter = 0L;
		variabilityElementList = new ArrayList<String>();
		transformerRules = new FeatureModelTransformerRules(prologEditorType);

		if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			header = GNUPrologConstraintSymbolsConstant.HEADER;
			endConstraintProgramFile = GNUPrologConstraintSymbolsConstant.END;
		} else {
			header = SWIPrologConstraintSymbolsConstant.HEADER;
			endConstraintProgramFile = SWIPrologConstraintSymbolsConstant.END;
			allDomainString = ("L ins 0..1,\n");
		}
	}

	/**
	 * Lee el xml para identificar las características raices
	 * 
	 * @param node
	 * @param tab
	 * @param plFile
	 */
	private void traverseDFSGPL(FeatureTreeNode node) {
		// Se agrega a la lista de características, la actual (se pone su
		// primera letra en mayúscula, se intercambian los espacios por _ y
		// se reemplazan los simbolos de + y - por Plus y Minus,
		// respectivamente)
		String featureName = evaluarPrimerCaracter(node.getName().charAt(0))
				+ node.getName().trim().substring(1).replaceAll(" ", "_")
						.replaceAll("\\-", "Minus").replaceAll("\\+", "Plus")
						.replaceAll("\\.", "dot").replaceAll("/", "");
		String constraintText = "";
		// Se consultan los elementos principales de la taxonomía de la
		// ontología

		// Característica raíz
		if (node instanceof RootNode) {

			variabilityElementList.add(featureName);
			// Para la raíz es #=1
			constraintText = transformerRules.getAssignRule(TransformerConstants.ONE).replace(
					TransformerConstants.FEATURE_1, featureName);
			mandatories.append(createConstraintForFile(constraintText));

		} else {

			FeatureTreeNode parent = (FeatureTreeNode) node.getParent();

			// Se agrega la característica hallada a la variable que
			// almacena las restricciones que representan opcionalidad
			String parentFeatureName = evaluarPrimerCaracter(parent.getName()
					.charAt(0))
					+ parent.getName().trim().substring(1).replaceAll(" ", "_")
							.replaceAll("\\-", "Minus")
							.replaceAll("\\+", "Plus").replaceAll("\\.", "dot")
							.replaceAll("/", "");

			// Para los elementos del modelo con excepción de las featureGroup
			// se crea la característica asociada como una instancia de la
			// ontología
			if (!(node instanceof FeatureGroup)) {

				// Se crea la característica opcional en el modelo
				variabilityElementList.add(featureName);

			}
			if (node instanceof SolitaireFeature) {
				// Característica opcional
				if (((SolitaireFeature) node).isOptional()) {

					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					constraintText = transformerRules
							.getOptionalRule()
							.replace(TransformerConstants.FEATURE_1,
									parentFeatureName)
							.replace(TransformerConstants.FEATURE_2,
									featureName);
					optional.append(createConstraintForFile(constraintText));

				}
				// Característica obligatoria
				else {

					// Se adiciona la depencencia opcional al modelo de
					// variabilidad
					constraintText = transformerRules
							.getMandatoryRule()
							.replace(TransformerConstants.FEATURE_1,
									parentFeatureName)
							.replace(TransformerConstants.FEATURE_2,
									featureName);
					mandatories.append(createConstraintForFile(constraintText));

				}

			}
			// Grupo de características
			else if (node instanceof FeatureGroup) {

				List<String> childFeaturesList = new ArrayList<String>();

				// Se obtiene el valor menor y mayor de cardinalidad
				Long minCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMin()));
				Long maxCardinality = new Long(
						String.valueOf(((FeatureGroup) node).getMax()));
				String constraintText2 = "";
				StringBuilder groupSet = new StringBuilder();

				// El -1 significa el * en la notación de splot,por lo que se
				// cuenta la cantidad máxima posible según la cantidad de hijos
				// q tenga el nodo
				if (maxCardinality == -1) {
					maxCardinality = (long) node.getChildCount();
				}

				// Se recorre los hijos de la cardinalidad para construir la
				// restricción
				Enumeration<FeatureTreeNode> childrenNodes = node.children();
				while (childrenNodes.hasMoreElements()) {
					FeatureTreeNode childNode = childrenNodes.nextElement();
					String childFeatureName = evaluarPrimerCaracter(childNode
							.getName().charAt(0))
							+ childNode.getName().trim().substring(1)
									.replaceAll(" ", "_")
									.replaceAll("\\-", "Minus")
									.replaceAll("\\+", "Plus")
									.replaceAll("\\.", "dot")
									.replaceAll("/", "");
					childFeaturesList.add(childFeatureName);
					groupSet.append(childFeatureName);
					// Si hay más elementos se adiciona una coma adicional
					if (childrenNodes.hasMoreElements()) {
						groupSet.append(ConstraintSymbolsConstant.PLUS);
					}
				}

				if (minCardinality.equals(maxCardinality)) {
					constraintText = transformerRules
							.getGroupalDependencyRule3()
							.replace(TransformerConstants.FEATURE_1,
									parentFeatureName)
							.replace(TransformerConstants.FEATURES_SET,
									groupSet);
				} else {
					// Para el caso en el que diga cuanto es y no un *
					constraintText = transformerRules
							.getGroupalDependencyRule1()
							.replace(TransformerConstants.FEATURE_1,
									parentFeatureName)
							.replace(TransformerConstants.FEATURES_SET,
									groupSet)
							.replace(TransformerConstants.M,
									minCardinality.toString());
					constraintText2 = transformerRules
							.getGroupalDependencyRule2()
							.replace(TransformerConstants.FEATURE_1,
									parentFeatureName)
							.replace(TransformerConstants.FEATURES_SET,
									groupSet)
							.replace(TransformerConstants.N,
									maxCardinality.toString());
				}

				// Se adicional la constraint
				groups.append(createConstraintForFile(constraintText));
				// Si se crearon dos restricciones se crea otro objeto de
				// restriccion
				if (!constraintText2.isEmpty()) {
					groups.append(createConstraintForFile(constraintText2));
				}

			}

		}
		// en esta parte se llama al mismo metodo con cada hijo del nodo
		// actual,
		// de manera recursiva
		for (int i = 0; i < node.getChildCount(); i++) {
			traverseDFSGPL((FeatureTreeNode) node.getChildAt(i));
		}
	}

	/**
	 * Adiciona las restricciones de inclusión y exclusión a la ontología, según
	 * lo expresado en el modelo
	 * 
	 * @param featureModel
	 */
	private void traverseConstraintsGPL(FeatureModel featureModel) {

		String constraintText = "";
		StringBuilder constraintFeaturesSet = null;

		for (PropositionalFormula formula : featureModel.getConstraints()) {
			constraintFeaturesSet = new StringBuilder();
			Iterator<BooleanVariable> iter = formula.getVariables().iterator();
			BooleanVariable element;

			while (iter.hasNext()) {
				element = (BooleanVariable) iter.next();
				FeatureTreeNode node = featureModel
						.getNodeByID(element.getID());
				String featureName = evaluarPrimerCaracter(node.getName()
						.charAt(0))
						+ node.getName().trim().substring(1)
								.replaceAll(" ", "_")
								.replaceAll("\\-", "Minus")
								.replaceAll("\\+", "Plus")
								.replaceAll("\\.", "dot").replaceAll("/", "");
				if (element.isPositive()) {
					constraintFeaturesSet.append(featureName);
				} else {
					constraintFeaturesSet.append(transformerRules
							.getNegativePropostionalElementRule()
							.replace(TransformerConstants.FEATURE_1,
									featureName));
				}

				// Si hay un siguiente elemento se adiciona un +
				if (iter.hasNext()) {
					constraintFeaturesSet
							.append(ConstraintSymbolsConstant.PLUS);

				}
			}

			if (constraintFeaturesSet.length() > 0) {
				constraintText = transformerRules
						.getPropositionalConstraintsRule().replace(
								TransformerConstants.FEATURES_SET,
								constraintFeaturesSet.toString());
				orRelations.append(createConstraintForFile(constraintText));
				constraintCounter++;
			}
		}
	}

	/**
	 * Para archivos creados con el generador de betty, se ajusta el : de la
	 * característica Root, y se la asigna un nombre para que pueda ser
	 * analizado
	 * 
	 * @param filePathInput
	 * @param fileInputName
	 * @param FilePathOutput
	 * @throws IOException
	 */
	public void fixBettyModel(String filePathInput, String xmlFileName,
			String filePathOutput) throws IOException {
		FileInputStream fstream = new FileInputStream(filePathInput);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		StringBuilder striBuilder = new StringBuilder();

		FileWriter fstreamWriter = new FileWriter(filePathOutput);
		BufferedWriter out = new BufferedWriter(fstreamWriter);

		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Se quita el prefijo que adicina Jena cuando guarda el OWL
			striBuilder.setLength(0);
			if (strLine.contains("r:")) {
				strLine = strLine.replaceAll("r:", ":r");
			}
			if (strLine.contains("name=\"\"")) {
				strLine = strLine.replaceAll("name=\"\"", "name=\""
						+ xmlFileName + "\"");
			}

			striBuilder.append(strLine);
			striBuilder.append("\n");
			out.write(striBuilder.toString());

		}

		out.close();
		// Se borra el anterior archivo creado
		new File(filePathInput).delete();

	}

	private String evaluarPrimerCaracter(char caracterInicial) {
		if ((caracterInicial >= 65 && caracterInicial <= 90)
				|| (caracterInicial >= 97 && caracterInicial <= 122))
			return String.valueOf(caracterInicial).toUpperCase();
		return "N".concat(String.valueOf(caracterInicial));
	}

	/**
	 * Controla todo el proceso de transformación
	 * @param inDTO
	 * @throws TransformerException
	 */
	public void transform(TransformerInDTO inDTO) throws TransformerException {

		init(inDTO);
		try {
			// Se lee el modelo de características, usando la librería que
			// provee
			// SPLOT
			FeatureModel featureModel = new XMLFeatureModel(
					inDTO.getInputPath(),
					XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
			featureModel.loadModel();

			// Restricciones Mandatory, optionales, grupales
			traverseDFSGPL(featureModel.getRoot());
			//Restricciones requires, excludes
			traverseConstraintsGPL(featureModel);

			// Se contruye la lista de características y de dominios
			StringBuilder featuresList = new StringBuilder("L=[");
			StringBuilder domainsList = new StringBuilder("fd_domain([");
			for (String variabilityElement : variabilityElementList) {
				featuresList.append(variabilityElement);
				featuresList.append(ConstraintSymbolsConstant.COMMA);
				//Para GNU prolog se requiere una instrucción diferente
				if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
					domainsList = domainsList.append(variabilityElement);
					domainsList.append(ConstraintSymbolsConstant.COMMA);
				}
			}
			featuresList.append("],");

			allFeaturesString = featuresList.toString().replace(",],", "],\n");
			if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
				domainsList.append("],");
				allDomainString = domainsList.toString().replace(",],",
						"], 0, 1),\n");
			}

			StringBuilder constraintProgramContent = new StringBuilder();
			constraintProgramContent.append(header);
			constraintProgramContent.append(allFeaturesString);
			constraintProgramContent.append(allDomainString);
			constraintProgramContent.append(mandatories);
			constraintProgramContent.append(optional);
			constraintProgramContent.append(orRelations);
			constraintProgramContent.append(groups);
			constraintProgramContent.append(endConstraintProgramFile);
			
			// Se escribe el archivo
			FileUtils.writePrologProgram(inDTO.getOutputPath(),
					constraintProgramContent.toString());
			System.out.println("Conversion complete");

		} catch (FeatureModelException e) {
			throw new TransformerException(e);

		}

	}

	/**
	 * Adiciona salto de línea y coma al final de cada restricción
	 * 
	 * @param constraintText
	 * @return
	 */
	private String createConstraintForFile(String constraintText) {
		return constraintText.concat(ConstraintSymbolsConstant.COMMA).concat(
				ConstraintSymbolsConstant.LF);
	}
}
