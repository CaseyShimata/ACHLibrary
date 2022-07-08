package com.loanpro.achlibrary.rule;


import com.loanpro.achlibrary.dictionary.DataType;

public class ACHDataTypeRule {
	private final Justifications justified;
	private final Paddings paddingType;
	private final LetterCases letterCase;
	private final String regex;

	private DataType dataType;

	private enum Justifications {NONE, LEFT, RIGHT}

	private enum Paddings {NONE, SPACES, ZEROS, NINES}

	private enum LetterCases {NA, INSENSITIVE, LOWER, UPPER}


	//if case PADDING use the first value in specificValues to determine the spacing
	private ACHDataTypeRule(DataType dataType, String[] enumPaddingSpecificOtherValues) {
		Justifications justified;
		Paddings paddingType;
		LetterCases letterCase;
		String regex;

		switch (dataType) {
			case ALPHA:
				justified = Justifications.LEFT;
				paddingType = Paddings.SPACES;
				letterCase = LetterCases.INSENSITIVE;
				regex = "^[A-Za-z ]+ *$";
				break;
			case NUMERIC:
				justified = Justifications.RIGHT;
				paddingType = Paddings.ZEROS;
				letterCase = LetterCases.NA;
				regex = "^0*[0-9]+$";
				break;
			case ALPHANUMERIC:
				justified = Justifications.NONE;
				paddingType = Paddings.NONE;
				letterCase = LetterCases.INSENSITIVE;
				regex = "^[0-9a-zA-Z ]+ *$";
				break;
			case ASCII:
				justified = Justifications.LEFT;
				paddingType = Paddings.SPACES;
				letterCase = LetterCases.INSENSITIVE;
				regex = "^[\\x00-\\xFF]+$";
				break;
			case CODES:
				justified = Justifications.LEFT;
				paddingType = Paddings.ZEROS;
				letterCase = LetterCases.NA;
				regex = "^[0-9A-Z]+0*$";
				break;
			case YYMMDD:
				justified = Justifications.NONE;
				paddingType = Paddings.NONE;
				letterCase = LetterCases.NA;
				regex = "^(?:(?:0){1}[0-9]|[1-8][0-9]|[9-9][0-9])(?:(?:0){1}[1-9]|[1-1][0-2])(?:(?:0){1}[1-9]|[1-2][0-9]|[3-3][0-1]$)";
				break;
			case HHMM:
				justified = Justifications.NONE;
				paddingType = Paddings.NONE;
				letterCase = LetterCases.NA;
				regex = "^(?:(?:0){1}[0-9]|[1-1][0-9]|[2-2][0-4])(?:(?:0){1}[0-9]|[1-4][0-9]|[5-5][0-9])$";
				break;
			case SPECIFICVALUES:
				justified = Justifications.NONE;
				paddingType = Paddings.NONE;
				letterCase = LetterCases.NA;
				regex = "^(?:";
				for (String value : enumPaddingSpecificOtherValues){
					regex += value + "|";
				}
				regex += ")$";
				break;
			case PADDING:
				justified = Justifications.NONE;
				paddingType = Paddings.SPACES;
				letterCase = LetterCases.NA;
				String paddingCharacter = enumPaddingSpecificOtherValues.length > 0 ? enumPaddingSpecificOtherValues[0] : " ";
				regex = "^[" + paddingCharacter + "]*$";
				break;
			case OTHER:
				justified = Justifications.NONE;
				paddingType = Paddings.NONE;
				letterCase = LetterCases.NA;
				String customRegex = enumPaddingSpecificOtherValues.length > 0 ? enumPaddingSpecificOtherValues[0] : "^[\\x00-\\xFF]+$";
				regex = customRegex;
				break;

			default:
				throw new IllegalStateException("Unexpected value while setting ACHDataTypeRule got: " + dataType);
		}
		this.dataType = dataType;
		this.justified = justified;
		this.paddingType = paddingType;
		this.letterCase = letterCase;
		this.regex = regex;
	}

	public static ACHDataTypeRule createNewInstance(DataType dataType, String[] specificValuesOrPaddingENUMPadding){
		return new ACHDataTypeRule(dataType, specificValuesOrPaddingENUMPadding);
	}

	public Justifications getJustified() {
		return justified;
	}

	public Paddings getPaddingType() {
		return paddingType;
	}

	public LetterCases getLetterCase() {
		return letterCase;
	}

	public String getRegex() {
		return regex;
	}

	public DataType getDataType() {
		return dataType;
	}
}
