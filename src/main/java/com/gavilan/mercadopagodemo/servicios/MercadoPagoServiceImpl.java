package com.gavilan.mercadopagodemo.servicios;

import com.gavilan.mercadopagodemo.excepciones.DemoException;
import com.gavilan.mercadopagodemo.modelo.Pedido;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    private final Logger log = LoggerFactory.getLogger(MercadoPagoServiceImpl.class);

    private final static String FAILURE_URL = "http://localhost:4200/mercado-pago/redirect/failure";
    private final static String SUCCESS_URL = "http://localhost:4200/mercado-pago/redirect/success";

    @Override
    public String crearPago() {
        Preference preference = new Preference();

        preference.setBackUrls(new BackUrls()
                .setFailure(FAILURE_URL).setSuccess(SUCCESS_URL));

        Item item = new Item();
        item.setTitle("Item prueba")
                .setQuantity(1)
                .setUnitPrice((float) 19.99);
        preference.appendItem(item);

        Preference result;

        try {
            result = preference.save();
        } catch (MPException e) {
            throw new DemoException(e.getMessage());
        }

        String approveUrl = result.getSandboxInitPoint();
        log.info(approveUrl);

        return approveUrl;
    }

    @Override
    public String crearPago(Pedido pedido) {
        return null;
    }
}
