package co.edu.eafit.tvl.expression;

import java.util.Vector;

import be.ac.info.fundp.TVLParser.SyntaxTree.Expression;
import be.ac.info.fundp.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.info.fundp.TVLParser.SyntaxTree.XorAggExpression;

public class GNUPrologXorAggExpression implements GNUPrologExpression {

	private XorAggExpression expression;

	public GNUPrologXorAggExpression(XorAggExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		Vector<Expression> expressions = expression.getExpressionList().getExpressions();
		String list = "";
		
		if (expressions.size() > 0) {
			list = GNUPrologExpressionTransformer.transform( new ParenthesesExpression(expressions.get(0))).toArithmeticForm();
			int i = 1;
			while ( i <= expressions.size()-1) {
				list = list.concat(" #<=> " + GNUPrologExpressionTransformer.transform( new ParenthesesExpression(expressions.get(i))).toArithmeticForm());
				i++;
			}
		}
		return list;
	}

}
