package test;

import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;

/**
 * User:krisjin
 * Date:2020-07-27
 */
public class FastUtilTest {


    public static void main(String[] args) {
        Int2DoubleOpenHashMap int2DoubleOpenHashMap = new Int2DoubleOpenHashMap(null,null);
        int2DoubleOpenHashMap.addTo(1, 22.1);

    }


    public static void dd() {

//        Int2Int2DoubleTable table = createTable(3);
//        assertThat(table.row(1).asMap()).hasSize(0);
//        assertThat(table.row(2).asMap()).hasSize(0);
//        table.putRow(1, new Int2DoubleMapRow(Int2DoubleMaps.singleton(1, 1.0)));
//        assertThat(table.row(1).asMap()).hasSize(1);
//        assertThat(table.row(1).asMap()).containsEntry(1, 1.0);
//        assertThat(table.row(2).asMap()).hasSize(0);
//        table.putRow(1, new Int2DoubleMapRow(Int2DoubleMaps.singleton(1, 3.0)));
//        assertThat(table.row(1).asMap()).hasSize(1);
//        assertThat(table.row(1).asMap()).containsEntry(1, 3.0);
//        assertThat(table.row(2).asMap()).hasSize(0);
//        table.putRow(1,
//                new Int2DoubleMapRow(new Int2DoubleOpenHashMap(ImmutableMap.of(1, 3.0, 2, 2.0))));
//        assertThat(table.row(1).asMap()).hasSize(2);
//        assertThat(table.row(1).asMap()).containsEntry(1, 3.0);
//        assertThat(table.row(1).asMap()).containsEntry(2, 2.0);
//        assertThat(table.row(2).asMap()).hasSize(0);
//        table.putRow(2, new Int2DoubleMapRow(Int2DoubleMaps.singleton(1, 2.0)));
//        assertThat(table.row(1).asMap()).hasSize(2);
//        assertThat(table.row(1).asMap()).containsEntry(1, 3.0);
//        assertThat(table.row(1).asMap()).containsEntry(2, 2.0);
//        assertThat(table.row(2).asMap()).hasSize(1);
//        assertThat(table.row(2).asMap()).containsEntry(1, 2.0);
    }
}
