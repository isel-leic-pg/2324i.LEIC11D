import kotlin.test.*

class TestAverageOfBest {
    @Test
    fun testNormalCases() {
        assertEquals(2.5F,listOf(2,2,1,3).averageOfBest(2))
        assertEquals(13.666667F,listOf(18,8,11,12).averageOfBest(3))
        assertEquals(11F,listOf(12,11,10).averageOfBest(3))
        assertEquals((7..10).average().toFloat(),(0..10).shuffled().averageOfBest(4))
    }
    @Test
    fun testMinusCases() {
        assertEquals(2F,listOf(1,3).averageOfBest(3))
        assertEquals(3F,listOf(1,3,1).averageOfBest(1))
        assertEquals(3F,listOf(1,3,3,1).averageOfBest(2))
    }
    @Test
    fun testZeroCases() {
        assertEquals(0F,listOf(1,3).averageOfBest(0))
        assertEquals(0F,listOf<Int>().averageOfBest(3))
    }
}