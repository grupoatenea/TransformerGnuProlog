package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MaxAggExpression;

public class GNUPrologMaxAggExpression implements GNUPrologExpression {

	private MaxAggExpression expression;

	public GNUPrologMaxAggExpression(MaxAggExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = expression.getExpressionList().getExpressions();
		String gnuMaxExpression = new StringBuilder().append( "max(" ).append( GNUPrologExpressionTransformer.transform(expressions.get(0)).toArithmeticForm() ) .append( ", " ).append( GNUPrologExpressionTransformer.transform(expressions.get(1)).toArithmeticForm() ).append( ")" ).toString();
		int i = 2;
		while ( i <= expressions.size() - 1 ) {
			gnuMaxExpression = new StringBuilder().append( "max(" ) .append( gnuMaxExpression ).append( ", " ).append( GNUPrologExpressionTransformer.transform(expressions.get(i)).toArithmeticForm() ).append( ")" ).toString();
			i++;
		}
		return gnuMaxExpression;
	}

}
