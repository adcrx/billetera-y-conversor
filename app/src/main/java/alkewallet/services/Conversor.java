package alkewallet.services;

import alkewallet.model.Moneda;

public class Conversor implements IConversor {

    public double obtenerTasa(Moneda origen, Moneda destino) {

        if (origen == destino) {
            return 1.0;
        }
        if (origen == Moneda.CLP && destino == Moneda.USD) return 1.0 / 950.0;
        if (origen == Moneda.USD && destino == Moneda.CLP) return 950.0;

        if (origen == Moneda.CLP && destino == Moneda.EUR) return 1.0 / 1030.0;
        if (origen == Moneda.EUR && destino == Moneda.CLP) return 1030.0;

        if (origen == Moneda.USD && destino == Moneda.EUR) return (950.0 / 1030.0);
        if (origen == Moneda.EUR && destino == Moneda.USD) return (1030.0 / 950.0);

        return 1.0;
    }

    @Override
        public double convertir(double monto, Moneda origen, Moneda destino) {
        double tasa = obtenerTasa(origen, destino);
        return monto * tasa;
    }
}
