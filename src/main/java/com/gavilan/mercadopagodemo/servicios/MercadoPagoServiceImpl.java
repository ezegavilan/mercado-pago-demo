package com.gavilan.mercadopagodemo.servicios;

import com.gavilan.mercadopagodemo.excepciones.DemoException;
import com.gavilan.mercadopagodemo.modelo.DetallePedido;
import com.gavilan.mercadopagodemo.modelo.Pedido;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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


        Item item1 = new Item();
        item1.setId("1234")
                .setTitle("Producto 1")
                .setQuantity(2)
                .setCurrencyId("ARS")
                .setUnitPrice((float) 75.56);

        preference.appendItem(item1);

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
        Preference preference = new Preference();
        preference.setBackUrls(new BackUrls().setFailure(FAILURE_URL).setSuccess(SUCCESS_URL));
        List<DetallePedido> items = pedido.getItems();

        ArrayList<Item> mpItems = new ArrayList<>();
        for (int i=0; i < items.size(); i++) {
            Item item = new Item();
            String title = items.get(i).getProducto().getNombre();
            Integer quantity = items.get(i).getCantidad();
            double unitPrice = items.get(i).getProducto().getPrecio();
            item.setId(String.valueOf(items.get(i).getProducto().getId()))
                    .setTitle(title).setDescription("Item nuevo")
                    .setQuantity(quantity)
                    .setUnitPrice((float) unitPrice);

            mpItems.add(item);
        }

        preference.setItems(mpItems);
        Preference result;

        try {
            result = preference.save();
        } catch (MPException e) {
            throw new DemoException(e.getMessage());
        }

        log.info(result.getSandboxInitPoint());
        return result.getSandboxInitPoint();
    }
}
