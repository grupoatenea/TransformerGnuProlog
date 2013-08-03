package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.AndAggExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;

public class GNUPrologAndAggExpression implements GNUPrologExpression {

	private AndAggExpression andAggExpression;
	
	public GNUPrologAndAggExpression(AndAggExpression expression) {
		this.andAggExpression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = andAggExpression.getExpressionList().getExpressions();
		String list = GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm();
		int i = 1;
		while ( i <= expressions.size()-1) {
			list = list.concat(",\n" + GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm());
			i++;
		}
		return list;
	}

	
}
