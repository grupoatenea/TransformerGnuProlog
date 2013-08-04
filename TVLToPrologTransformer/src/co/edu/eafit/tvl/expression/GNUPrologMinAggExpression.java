package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MinAggExpression;

public class GNUPrologMinAggExpression implements GNUPrologExpression {

	private MinAggExpression expression;

	public GNUPrologMinAggExpression(MinAggExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = expression.getExpressionList().getExpressions();
		String gnuMaxExpression = new StringBuilder().append( "min(" ).append( GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm() ) .append( ", " ).append( GNUPrologTransformer.transform(expressions.get(1)).toArithmeticForm() ).append( ")" ).toString();
		int i = 2;
		while ( i <= expressions.size() - 1 ) {
			gnuMaxExpression = new StringBuilder().append( "min(" ) .append( gnuMaxExpression ).append( ", " ).append( GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm() ).append( ")" ).toString();
			i++;
		}
		return gnuMaxExpression;
	}

}
