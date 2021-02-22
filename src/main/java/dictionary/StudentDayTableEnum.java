package dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public enum StudentDayTableEnum {

	Zero("0", "0"), One("1", "1"), Two("2", "2"), Three("3", "3"), Four("4", "4"), Five("5", "5"), Six("6", "6"), Seven("7", "7"), Eight("8", "8"), Nine("9", "9"), 
	A("a", "8"), B("b", "7"), C("c", "6"), D("d", "9"), E("e", "1"), F("f", "4"), G("g", "2"), H("h", "4"), I("i", "2"), J("j", "8"), K("k", "9"), L("l", "8"), M("m", "3"), 
	N("n", "5"), O("o", "0"), P("p", "6"), Q("q", "1"), R("r", "5"), S("s", "2"), T("t", "7"), U("u", "4"), V("v", "3"), W("w", "9"), X("x", "0"), Y("y", "3"), Z("z", "5");

	private final String prefix;
	private final String code;

	private StudentDayTableEnum(String prefix, String code) {
		this.prefix = prefix;
		this.code = code;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getCode() {
		return code;
	}

	public static StudentDayTableEnum parse(String tenantCode) {
		if(StringUtils.isBlank(tenantCode)){
			return null;
		}
		String prefix = tenantCode.substring(0,1);
		StudentDayTableEnum result = null;
        switch(prefix) {   
            case "0":
                result = Zero;
                break;
            case "1":
                result = One;
                break;
            case "2":
            	result = Two;
            	break;
            case "3":
            	result = Three;
            	break;
            case "4":
            	result = Four;
            	break;
            case "5":
            	result = Five;
            	break;
            case "6":
            	result = Six;
            	break;
            case "7":
            	result = Seven;
            	break;
            case "8":
            	result = Eight;
            	break;
            case "9":
            	result = Nine;
            	break;
            case "a":
            	result = A;
            	break;
            case "b":
            	result = B;
            	break;
            case "c":
            	result = C;
            	break;
            case "d":
            	result = D;
            	break;
            case "e":
            	result = E;
            	break;
            case "f":
            	result = F;
            	break;
            case "g":
            	result = G;
            	break;
            case "h":
            	result = H;
            	break;
            case "i":
            	result = I;
            	break;
            case "j":
            	result = J;
            	break;
            case "k":
            	result = K;
            	break;
            case "l":
            	result = L;
            	break;
            case "m":
            	result = M;
            	break;
            case "n":
            	result = N;
            	break;
            case "o":
            	result = O;
            	break;
            case "p":
            	result = P;
            	break;
            case "q":
            	result = Q;
            	break;
            case "r":
            	result = R;
            	break;
            case "s":
            	result = S;
            	break;
            case "t":
            	result = T;
            	break;
            case "u":
            	result = U;
            	break;
            case "v":
            	result = V;
            	break;
            case "w":
            	result = W;
            	break;
            case "x":
            	result = X;
            	break;
            case "y":
            	result = Y;
            	break;
            case "z":
            	result = Z;
            	break;
            	
        }
        return result;
    }
	
	public static List<StudentDayTableEnum> getEnumValues() {
        return Arrays.asList(values());
    }
	
	public static List<String> getEnumCodes(){
		List<String> statisticsTableEnumCodes = new ArrayList<>();
		statisticsTableEnumCodes.add(StudentDayTableEnum.Zero.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Two.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Three.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Four.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Five.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Six.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Seven.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Eight.getCode());
		statisticsTableEnumCodes.add(StudentDayTableEnum.Nine.getCode());
		return statisticsTableEnumCodes;
	}
}