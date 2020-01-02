package interview;

/**
 * 枚举
 *
 * @author tonysu,
 * @version 1.0v.
 * @Create 2019/12/20 12:23 AM,
 */
public enum CountryEnum{

	ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

	private Integer retCode;
	private String retMessage;

	CountryEnum(Integer retCode, String retMessage){
		this.retCode = retCode;
		this.retMessage = retMessage;
	}

	public static CountryEnum forEach_CountryEnum(int index){
		CountryEnum[] countryEnums = CountryEnum.values();
		for(CountryEnum countryEnum : countryEnums){
			if(countryEnum.getRetCode() == index){
				return countryEnum;
			}
		}
		return null;
	}

	public Integer getRetCode(){
		return retCode;
	}

	public String getRetMessage(){
		return retMessage;
	}
}
