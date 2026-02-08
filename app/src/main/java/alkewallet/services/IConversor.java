package alkewallet.services;

import alkewallet.model.Moneda;

public interface IConversor {

    double convertir(double monto, Moneda origen, Moneda destino);

}
