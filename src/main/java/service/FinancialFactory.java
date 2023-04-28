package service;

import model.enumfiles.FinancialProviderType;
import model.vandar.VandarConfig;
import model.zarinpal.ZarinPalConfig;

public class FinancialFactory {

    private VandarConfig vandarConfig;
    private ZarinPalConfig zarinPalConfig;

    public FinancialFactory(VandarConfig vandarConfig, ZarinPalConfig zarinPalConfig) {
        this.vandarConfig = vandarConfig;
        this.zarinPalConfig = zarinPalConfig;
    }

    public IFinancial generateFinancial(FinancialProviderType financialProviderType) throws Exception {
        switch (financialProviderType) {
            case VANDAR:
                return new VandarImpl(vandarConfig);
            case ZARINPAL:
                return new ZarinPalImpl(zarinPalConfig);

            default:
                throw new IllegalStateException();
        }
    }



}
