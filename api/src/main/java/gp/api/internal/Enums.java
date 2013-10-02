package gp.api.internal;

public class Enums {

    public static <T extends Enum & Identificado> T getEnum(int id, Class<T> klass) {
        for (T t : klass.getEnumConstants()) {
            if (id == t.getId()) {
                return t;
            }
        }
        throw new IllegalArgumentException(String.format("Enum da classe [%s] desconhecido com id [%d]", klass, id));
    }
}
