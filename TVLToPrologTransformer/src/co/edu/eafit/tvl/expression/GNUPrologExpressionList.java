package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ExpressionList;

public class GNUPrologExpressionList implements GNUPrologExpression {

	private ExpressionList expressionList;
	
	public GNUPrologExpressionList(ExpressionList expression) {
		this.expressionList = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = expressionList.getExpressions();
		String list = GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm();
		int i = 1;
		while ( i <= expressions.size()-1) {
			list = list.concat(", "+ GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm());
			i++;
		}
		return list;
	}
}
