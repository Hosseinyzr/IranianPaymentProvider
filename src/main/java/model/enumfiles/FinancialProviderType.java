package model.enumfiles;

import lombok.Getter;

@Getter
public enum FinancialProviderType {

    ZARINPAL(0, "زرین پال"),
    VANDAR(1, "وندار"),
    ;

    private final int value;
    private final String name;

    FinancialProviderType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static FinancialProviderType of(String name) {
        for (FinancialProviderType item : values())
            if (item.name.equals(name))
                return item;
        throw new IllegalArgumentException();
    }

    public static FinancialProviderType of(int value) {
        for (FinancialProviderType item : values())
            if (item.value == value)
                return item;
        throw new IllegalArgumentException();
    }

}
