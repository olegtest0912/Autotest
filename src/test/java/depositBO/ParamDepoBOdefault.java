package depositBO;


import com.google.gson.reflect.TypeToken;
import data.IParamsProvider;
import data.JsonParamsProvider;
import data.model.ParamDepoData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class ParamDepoBOdefault extends SettingsBO {
    @Parameterized.Parameters(name = "{0}" + " " + "{2}" + " " + "{3}")
    public static Collection<Object[]> data() {


        // Expected PaySystems
        String certus = "https://api.certus.finance/FE/rest/tx/purchase/w/execute";
        String accentpay = "https://cashier.paywallk.com/payment?payment_id";
        String paymentCenter = "https://paygatedirect.com/v2/webpay?token";
        String payboutique = "https://pay.qiwi.com/?token";
        String xpate = "https://m.xpate.com/p/";

        // User data by Countries
        String[] sng = {"rohef41395@fazmail.net", "123456BB"};
        String[] russia = {"mitaxej795@gilfun.com", "123456BB"};
        String[] other = {"selenium1235744@qafinmax.com", "123456Aa"};
        String[] UK = {"selenium1239195@qafinmax.com", "123456Aa"};
        String[] europe = {"dmitriy.qc3@bk.ru", "123456"};
        String[] baltic = {"sadod29964@provlst.com", "123456BB"};
        String[] Australia = {"Selertdy545678i@autotest.com", "123456Aa"};

      return Arrays.asList(new Object[][]{

                        {"Russia", russia, "visa", "250", accentpay},
                        {"Russia", russia, "visa", "1000", paymentCenter},
                        {"Russia", russia, "visa", "2000", accentpay},
                        {"Russia", russia, "master", "250", paymentCenter},
                        {"Russia", russia, "master", "1000", paymentCenter},
                        {"Russia", russia, "mir", "50", accentpay},
                        {"Russia", russia, "mir", "1000", paymentCenter},
                        {"Russia", russia, "mir", "2000", accentpay},

                        {"Sng", sng, "visa", "99", payboutique},
                        {"Sng", sng, "visa", "2000", accentpay},
                        {"Sng", sng, "visa", "3100", accentpay},
                        {"Sng", sng, "master", "99", accentpay},
                        {"Sng", sng, "master", "500", payboutique},

                        {"Baltic", baltic, "visa", "200", payboutique},
                        {"Baltic", baltic, "visa", "1000", accentpay},
                        {"Baltic", baltic, "master", "250", payboutique},
                        {"Baltic", baltic, "master", "1000", accentpay},
                        {"Baltic", baltic, "master", "2000", payboutique},

                        {"Europe", europe, "visa", "200", accentpay},
                        {"Europe", europe, "visa", "1000", certus},
                        {"Europe", europe, "master", "200", certus},
                        {"Europe", europe, "master", "1000", certus},

                        {"Australia", Australia, "visa", "200", certus},
                        {"Australia", Australia, "visa", "1000", accentpay},
                        {"Australia", Australia, "master", "200", accentpay},
                        {"Australia", Australia, "master", "1000", certus},

                        {"Other", other, "visa", "200", certus},
                        {"Other", other, "visa", "1000", accentpay},
                        {"Other", other, "master", "200", accentpay},
                        {"Other", other, "master", "1000", certus},

                        {"uk", UK, "credit_accentpay", "200", certus},
                        {"uk", UK, "credit_accentpay", "1600", certus}

                }
        );


    }



    private String description;
    private String[] country;
    private String button;
    private String value;
    private String paySystem;

    public ParamDepoBOdefault(String description, String[] country, String button, String value, String paySystem) {
        this.description = description;
        this.country = country;
        this.button = button;
        this.value = value;
        this.paySystem = paySystem;
    }

    @Test
    public void depositBO() throws InterruptedException, IOException {
        LoginToBO loginToBO = PageFactory.initElements(driver, LoginToBO.class);
        loginToBO.loginToBo(country[0], country[1]);
        ClickPaymentBO clickPayment = PageFactory.initElements(driver, ClickPaymentBO.class);
        clickPayment.checkDeposit(button, value);
        clickPayment.checkPaysystem(paySystem);
        clickPayment.checkStatus();
    }
}


