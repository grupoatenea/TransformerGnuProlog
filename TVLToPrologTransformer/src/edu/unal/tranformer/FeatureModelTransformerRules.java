package edu.unal.tranformer;

import edu.unal.constants.ConstraintSymbolsConstant;
import edu.unal.constants.GNUPrologConstraintSymbolsConstant;
import edu.unal.constants.SWIPrologConstraintSymbolsConstant;
import edu.unal.constants.TransformerConstants;
import edu.unal.model.enums.SolverEditorType;

public class FeatureModelTransformerRules {

	private static String equivalenteSymbolString;
		
	public FeatureModelTransformerRules(SolverEditorType prologEditorType) {
		super();
		if (prologEditorType.equals(SolverEditorType.GNU_PROLOG)) {
			equivalenteSymbolString = GNUPrologConstraintSymbolsConstant.EQUIVALENT;
			
		} else {
			equivalenteSymbolString = SWIPrologConstraintSymbolsConstant.EQUIVALENT;
			
		}
	}
	
	public String getMandatoryRule(){
		StringBuilder rule= new StringBuilder();
		//A <=> B
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(equivalenteSymbolString);
		rule.append(TransformerConstants.FEATURE_2);
		return rule.toString();
	}
	
	public String getOptionalRule(){
		StringBuilder rule= new StringBuilder();
		//A >= B
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.MORE_OR_EQUALS);
		rule.append(TransformerConstants.FEATURE_2);
		return rule.toString();
	}
	
	public String getAssignRule(String valueToAssing){
		StringBuilder rule= new StringBuilder();
		//A #= Valor
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.ASSIGN);
		rule.append(valueToAssing);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule1(){
		StringBuilder rule= new StringBuilder();
		//m * P <= SUM features 
		// Feature1=parent feature
		rule.append(TransformerConstants.M);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.LESS_OR_EQUALS);
		rule.append(TransformerConstants.FEATURES_SET);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule2(){
		StringBuilder rule= new StringBuilder();
		//SUM features <= n * P 
		// Feature1=parent feature
		rule.append(TransformerConstants.FEATURES_SET);
		rule.append(ConstraintSymbolsConstant.LESS_OR_EQUALS);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.N);
		return rule.toString();
	}
	
	public String getGroupalDependencyRule3(){
		StringBuilder rule= new StringBuilder();
		//1 * P #= SUM features 
		rule.append(ConstraintSymbolsConstant.ONE);
		rule.append(ConstraintSymbolsConstant.MULTIPLY);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.ASSIGN);
		rule.append(TransformerConstants.FEATURES_SET);
		return rule.toString();
	}
	
	public String getPropositionalConstraintsRule(){
		// prop1 + prop2 +--- #>0" EJM (1 - F2) + (1 - F8) #> 0,
		StringBuilder rule= new StringBuilder();
		rule.append(TransformerConstants.FEATURES_SET);
		rule.append(ConstraintSymbolsConstant.MORE);
		rule.append(ConstraintSymbolsConstant.ZERO);
		return rule.toString();
	}
	
	public String getNegativePropostionalElementRule(){
		//( 1-featureName)
		StringBuilder rule= new StringBuilder();
		rule.append(ConstraintSymbolsConstant.OPEN_PARENTHESIS);
		rule.append(ConstraintSymbolsConstant.ONE);
		rule.append(ConstraintSymbolsConstant.SUBSTRACTION);
		rule.append(TransformerConstants.FEATURE_1);
		rule.append(ConstraintSymbolsConstant.CLOSE_PARENHESIS);
		return rule.toString();
	}
	
	
	public String getGroupalDependencyName3(){
		StringBuilder dependencyName= new StringBuilder();
		//Parent TO groupedFeatures
		dependencyName.append(TransformerConstants.FEATURE_1);
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(TransformerConstants.FEATURES_SET);
		return dependencyName.toString();
	}
	
	public String getGroupalDependencyName1(){
		//Parent TO groupedFeatures LowCardinality
		StringBuilder dependencyName= new StringBuilder(getGroupalDependencyName3());
		dependencyName.append(TransformerConstants.LOW_CARDINALITY);
		return dependencyName.toString();
	}
	
	public String getGroupalDependencyName2(){
		//Parent TO groupedFeatures UpperCardinality
		StringBuilder dependencyName= new StringBuilder(getGroupalDependencyName3());
		dependencyName.append(TransformerConstants.UPPER_CARDINALITY);
		return dependencyName.toString();
	}
	
	public String getPropositionalName(){
		StringBuilder dependencyName= new StringBuilder();
		//Dependency F1-F2-F3
		dependencyName.append(TransformerConstants.DEPENDENCY);
		dependencyName.append(TransformerConstants.FEATURES_SET);
		return dependencyName.toString();
	}
	
	public String getRootDependencyName(){
		StringBuilder dependencyName= new StringBuilder();
		//Model root: feature1"
		dependencyName.append(TransformerConstants.MODEL_ROOT);
		dependencyName.append(TransformerConstants.FEATURE_1);
		return dependencyName.toString();
	}
	
	public String getMandatoryOptionalDependencyName(){
		StringBuilder dependencyName= new StringBuilder();
		//feature1 TO feature2"
		dependencyName.append(TransformerConstants.FEATURE_1);
		dependencyName.append(TransformerConstants.TO);
		dependencyName.append(TransformerConstants.FEATURE_2);
		return dependencyName.toString();
	}
	
}
