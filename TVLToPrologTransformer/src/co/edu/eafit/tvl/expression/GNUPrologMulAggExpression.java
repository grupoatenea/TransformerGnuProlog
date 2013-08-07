package co.edu.eafit.tvl.expression;

import java.util.Vector;


import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.MulAggExpression;

public class GNUPrologMulAggExpression implements GNUPrologExpression {
    
	private MulAggExpression mulAggExpression;
	public GNUPrologMulAggExpression(MulAggExpression expression) {
		this.mulAggExpression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = mulAggExpression.getExpressionList().getExpressions();
		String list = "(" + GNUPrologTransformer.transform(expressions.get(0)).toArithmeticForm();
		int i = 1;
		while ( i <= expressions.size()-1) {
			list = list.concat(" * " + GNUPrologTransformer.transform(expressions.get(i)).toArithmeticForm());
			i++;
		}
		return list+")";
	}

}
