package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.SumAggExpression;

public class GNUPrologSumAggExpression implements GNUPrologExpression {

	private SumAggExpression expression;

	public GNUPrologSumAggExpression(SumAggExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = expression.getExpressionList().getExpressions();
		String list = GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm();
		int i = 1;
		while ( i <= expressions.size()-1) {
			list = list.concat(" + " + GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm());
			i++;
		}
		return list;
	}
	
	
}
