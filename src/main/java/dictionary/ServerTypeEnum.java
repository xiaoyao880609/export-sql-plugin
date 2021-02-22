package dictionary;

import java.util.Arrays;
import java.util.List;

public enum ServerTypeEnum {
	DEV(1, "测试"),
	REAL(2, "正式");
    
    private Integer                                value;
    private String                                 label;
    
	private ServerTypeEnum(Integer value, String label) {
		this.value = value;
		this.label = label;
	}

	public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static ServerTypeEnum parse(int value) {
        ServerTypeEnum result = null;
        switch(value) {   
            case 1:
                result = ServerTypeEnum.DEV;
                break;
            case 2:
                result = ServerTypeEnum.REAL;
                break;
        }
        return result;
    }

    public static List<ServerTypeEnum> getEnumValues() {
        return Arrays.asList(values());
    }
}
