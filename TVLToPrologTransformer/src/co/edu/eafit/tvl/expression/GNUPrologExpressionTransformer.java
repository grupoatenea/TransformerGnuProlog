package co.edu.eafit.tvl.expression;

import be.ac.info.fundp.TVLParser.SyntaxTree.AbsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AndExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.AvgAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.DivideExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExcludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExpressionList;
import be.ac.info.fundp.TVLParser.SyntaxTree.FalseExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.GEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.GreaterExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IfAndOnlyIfExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.InExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IncludesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.IntExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.InverseImpliesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LEQExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.LowerExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MaxAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MinAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MinusExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MulAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.NotEqualsExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.NotExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.OrAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.OrExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.PlusExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.QuestExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.RealExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SumAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.TimesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.TrueExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.XorAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ZeroExpression;

public abstract class GNUPrologExpressionTransformer {

	public static GNUPrologExpression transform( Expression tvlExpression ){
		
		if (tvlExpression instanceof ZeroExpression){
			return new GNUPrologZeroExpression();
		}
		
		if (tvlExpression instanceof NotExpression){
			NotExpression expression = (NotExpression)tvlExpression;
			return new GNUPrologNotExpression(expression);
		}
		
		if (tvlExpression instanceof LongIDExpression){
			LongIDExpression expression = (LongIDExpression)tvlExpression;
			return new GNUPrologLongIDExpression(expression);
		}
		
		if (tvlExpression instanceof AbsExpression){
			AbsExpression expression = (AbsExpression)tvlExpression;
			return new GNUPrologAbsExpression(expression);
		}
		
		if (tvlExpression instanceof ImpliesExpression){
			ImpliesExpression expression = (ImpliesExpression)tvlExpression;
			return new GNUPrologImpliesExpression(expression);
		}
		
		if (tvlExpression instanceof IncludesExpression){
			IncludesExpression expression = (IncludesExpression)tvlExpression;
			return new GNUPrologIncludesExpression(expression);
		}
		
		if (tvlExpression instanceof ExcludesExpression){
			ExcludesExpression expression = (ExcludesExpression)tvlExpression;
			return new GNUPrologExcludesExpression(expression);
		}
		
		if (tvlExpression instanceof PlusExpression){
			PlusExpression expression = (PlusExpression)tvlExpression;
			return new GNUPrologPlusExpression(expression);
		}
		
		if (tvlExpression instanceof DivideExpression){
			DivideExpression expression = (DivideExpression)tvlExpression;
			return new GNUPrologDivideExpression(expression);
		}
		
		if (tvlExpression instanceof RealExpression){
			RealExpression expression = (RealExpression)tvlExpression;
			return new GNUPrologRealExpression(expression);
		}
		
		if (tvlExpression instanceof IntExpression){
			IntExpression expression = (IntExpression)tvlExpression;
			return new GNUPrologIntExpression(expression);
		}
		
		if (tvlExpression instanceof ExpressionList){
			ExpressionList expression = (ExpressionList)tvlExpression;
			return new GNUPrologExpressionList(expression);
		}
		
		if (tvlExpression instanceof AndAggExpression){
			AndAggExpression expression = (AndAggExpression)tvlExpression;
			return new GNUPrologAndAggExpression(expression);
		}
		
		if (tvlExpression instanceof AndExpression){
			AndExpression expression = (AndExpression)tvlExpression;
			return new GNUPrologAndExpression(expression);
		}
		
		if (tvlExpression instanceof OrExpression){
			OrExpression expression = (OrExpression)tvlExpression;
			return new GNUPrologOrExpression(expression);
		}
		
		if (tvlExpression instanceof AvgAggExpression){
			AvgAggExpression expression = (AvgAggExpression)tvlExpression;
			return new GNUPrologAvgAggExpression(expression);
		}
		
		if (tvlExpression instanceof LowerExpression){
			LowerExpression expression = (LowerExpression)tvlExpression;
			return new GNUPrologLowerExpression(expression);
		}
		
		if (tvlExpression instanceof GreaterExpression){
			GreaterExpression expression = (GreaterExpression)tvlExpression;
			return new GNUPrologGreaterExpression(expression);
		}
		
		if (tvlExpression instanceof LEQExpression){
			LEQExpression expression = (LEQExpression)tvlExpression;
			return new GNUPrologLowerOrThanEqualsExpression(expression);
		}
		
		if (tvlExpression instanceof GEQExpression){
			GEQExpression expression = (GEQExpression)tvlExpression;
			return new GNUPrologGreaterOrThanEqualsExpression(expression);
		}
		
		if (tvlExpression instanceof EqualsExpression){
			EqualsExpression expression = (EqualsExpression)tvlExpression;
			return new GNUPrologEqualsExpression(expression);
		}
		
		if (tvlExpression instanceof NotEqualsExpression){
			NotEqualsExpression expression = (NotEqualsExpression)tvlExpression;
			return new GNUPrologNotEqualsExpression(expression);
		}
		
		if (tvlExpression instanceof SumAggExpression){
			SumAggExpression expression = (SumAggExpression)tvlExpression;
			return new GNUPrologSumAggExpression(expression);
		}
		
		if (tvlExpression instanceof QuestExpression){
			QuestExpression expression = (QuestExpression)tvlExpression;
			return new GNUPrologQuestExpression(expression);
		}
		
		if (tvlExpression instanceof IfAndOnlyIfExpression){
			IfAndOnlyIfExpression expression = (IfAndOnlyIfExpression)tvlExpression;
			return new GNUPrologIfAndOnlyIfExpression(expression);
		}
		
		if (tvlExpression instanceof FalseExpression){
			return new GNUPrologFalseExpression();
		}
		
		if (tvlExpression instanceof TrueExpression){
			return new GNUPrologTrueExpression();
		}
		
		if (tvlExpression instanceof InExpression){
			InExpression expression = (InExpression)tvlExpression;
			return new GNUPrologInExpression(expression);
		}
		
		if (tvlExpression instanceof ParenthesesExpression){
			ParenthesesExpression expression = (ParenthesesExpression)tvlExpression;
			return new GNUPrologParenthesesExpression(expression);
		}
		
		if (tvlExpression instanceof MaxAggExpression){
			MaxAggExpression expression = (MaxAggExpression)tvlExpression;
			return new GNUPrologMaxAggExpression(expression);
		}
		
		if (tvlExpression instanceof MinAggExpression){
			MinAggExpression expression = (MinAggExpression)tvlExpression;
			return new GNUPrologMinAggExpression(expression);
		}
		
		if (tvlExpression instanceof MinusExpression){
			MinusExpression expression = (MinusExpression)tvlExpression;
			return new GNUPrologMinusExpression(expression);
		}
		
		if (tvlExpression instanceof MulAggExpression){
			MulAggExpression expression = (MulAggExpression)tvlExpression;
			return new GNUPrologMulAggExpression(expression);
		}
		
		if (tvlExpression instanceof OrAggExpression){
			OrAggExpression expression = (OrAggExpression)tvlExpression;
			return new GNUPrologOrAggExpression(expression);
		}
		
		if (tvlExpression instanceof InverseImpliesExpression){
			InverseImpliesExpression expression = (InverseImpliesExpression)tvlExpression;
			return new GNUPrologInverseImpliesExpression(expression);
		}
		
		if (tvlExpression instanceof TimesExpression){
			TimesExpression expression = (TimesExpression)tvlExpression;
			return new GNUPrologTimesExpression(expression);
		}
		
		if (tvlExpression instanceof XorAggExpression){
			XorAggExpression expression = (XorAggExpression)tvlExpression;
			return new GNUPrologXorAggExpression(expression);
		}
		
		throw new IllegalArgumentException("TVL expression " + tvlExpression.getClass().getSimpleName() + " is not defined for GNUProlog");
	}

}
