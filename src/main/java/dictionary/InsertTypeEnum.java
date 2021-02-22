package dictionary;

import java.util.Arrays;
import java.util.List;

public enum InsertTypeEnum {
	ALL(0, "全部"),
	EXCLUDE_CURRENT_MONTH(1, "排除当前月数据"),
	CURRENT_MONTH(2, "当前月数据");
    
    private Integer                                value;
    private String                                 label;
    
	private InsertTypeEnum(Integer value, String label) {
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

    public static InsertTypeEnum parse(int value) {
        InsertTypeEnum result = null;
        switch(value) {   
            case 0:
                result = InsertTypeEnum.ALL;
                break;
            case 1:
                result = InsertTypeEnum.EXCLUDE_CURRENT_MONTH;
                break;
            case 2:
                result = InsertTypeEnum.CURRENT_MONTH;
                break;
        }
        return result;
    }

    public static List<InsertTypeEnum> getEnumValues() {
        return Arrays.asList(values());
    }
}
