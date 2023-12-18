import kotlin.test.*

class TestListToText {
    @Test
    fun toTextCases() {
        assertEquals("12,15,8,7",listOf(12, 15, 8, 7).toText())
        assertEquals("1,2,3,4,5,6",listOf(1,2,3,4,5,6).toText())
        assertEquals("27",listOf(27).toText())
        assertEquals("",listOf<Int>().toText())
    }
}