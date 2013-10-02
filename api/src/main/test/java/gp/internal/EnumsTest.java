package gp.internal;

import gp.api.internal.Enums;
import gp.api.internal.Identificado;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EnumsTest {

    @Test
    public void getEnumComIdConhecido() throws Exception {
        assertEquals(Teste.ITEM1, Enums.getEnum(1, Teste.class));
        assertEquals(Teste.ITEM2, Enums.getEnum(2, Teste.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getEnumComIdDesconhecido() throws Exception {
        Enums.getEnum(3, Teste.class);
    }

    private static enum Teste implements Identificado {
        ITEM1(1), ITEM2(2);

        private final int id;

        private Teste(int id) {
            this.id = id;
        }

        @Override
        public int getId() {
            return id;
        }
    }
}
