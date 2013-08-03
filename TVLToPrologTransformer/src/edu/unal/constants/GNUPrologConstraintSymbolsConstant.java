package edu.unal.constants;

public class GNUPrologConstraintSymbolsConstant extends ConstraintSymbolsConstant {


	public static final String GNU_CONSTRAINTS_SYMBOLS[] = { "#<=>", "#<=>",
		"##", "#\\==>", "#\\/\\", "#\\/"};
	
	//OPERATION CONSTANTS FOR GNU PROLOG
	public static final String EQUIVALENT="#<=>";
	public static final String NOT_EQUIVALENT="#\\<=>";
	public static final String NOT_EQUIVALENT_2="##";
	public static final String NOT_IMPLIES="#\\==>";
	public static final String NAND="#\\/\\";
	public static final String NOR="#\\/";
	
	// HEADER FOR GNU PROLOG 
	public static final String HEADER="productline(L):-\n";
	
	//END FILE
	public static final String END="fd_labeling(L).";

}
