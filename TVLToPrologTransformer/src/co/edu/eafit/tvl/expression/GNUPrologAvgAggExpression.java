package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.AvgAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;

public class GNUPrologAvgAggExpression implements GNUPrologExpression {
	
	private AvgAggExpression avgAggExpression;

	public GNUPrologAvgAggExpression(AvgAggExpression expression) {
		this.avgAggExpression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = avgAggExpression.getExpressionList().getExpressions();
		String list = "(" + GNUPrologExpressionTransformer.transform(expressions.get(0)).toArithmeticForm();
		int i = 1;
		while ( i <= expressions.size()-1) {
			list = list.concat(" + " + GNUPrologExpressionTransformer.transform(expressions.get(i)).toArithmeticForm());
			i++;
		}
		return list + ") / " + expressions.size();
	}

	
}
