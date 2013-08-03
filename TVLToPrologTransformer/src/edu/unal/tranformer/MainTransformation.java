package edu.unal.tranformer;

import javax.xml.transform.TransformerException;

import edu.unal.DTO.TransformerInDTO;
import edu.unal.model.enums.SolverEditorType;

public class MainTransformation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TransformerInDTO inDTO = new TransformerInDTO();
//		inDTO.setInputPath("models/SPLOTModels/SPLOTExample1.sxfm");
		inDTO.setInputPath("models/SPLOTModels/Seguros.sxfm");
//		inDTO.setOutputPath("models/ConstraintModels/GNUPrologExample1.pl");
		inDTO.setOutputPath("models/ConstraintModels/Segurostvl.pl");
		inDTO.setSolverEditorType(SolverEditorType.GNU_PROLOG);
		
		FeatureModelSPLOTransformer transformer = new FeatureModelSPLOTransformer();
		try {
			transformer.transform(inDTO);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
