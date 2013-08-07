package co.edu.eafit.tvl.expression;

import co.edu.eafit.tvl.transformation.GNUPrologNamesContainer;
import be.ac.info.fundp.TVLParser.SyntaxTree.IncludesExpression;

public class GNUPrologIncludesExpression implements GNUPrologExpression {

	private IncludesExpression expression;

	public GNUPrologIncludesExpression(IncludesExpression expression) {
		this.expression = expression;
	}

	@Override
	public String toArithmeticForm() {
		String name1 = GNUPrologNamesContainer.getInstance().getName(expression.getLongID1());
		String name2 = GNUPrologNamesContainer.getInstance().getName(expression.getLongID2());
		return name1 + " #==> " + name2;
	}

	
}
