package ar.com.ada.api.aladas.entities;

public class Pais {

    public enum PaisEnum {
        ARGENTINA(32),BRASIL(76), FRANCIA(250), ARUBA(533), PORTUGAL(620), ESPAÑA(724), ESTADOS_UNIDOS(840), VENEZUELA(862), COLOMBIA(170), SUIZA(756);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private PaisEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static PaisEnum parse(Integer id) {
            PaisEnum status = null; // Default
            for (PaisEnum item : PaisEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    public enum TipoDocuEnum {
        DNI(1), PASAPORTE(2);

        private final Integer value;

        private TipoDocuEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoDocuEnum parse(Integer id) {
            TipoDocuEnum status = null;
            for (TipoDocuEnum item : TipoDocuEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;

        }
    }
}
