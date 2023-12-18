import kotlin.test.*

class TestStudentToText {
    @Test
    fun studentToTextCases() {
        val student1 = Student(49123,"João Matos",listOf(15,12,7,11))
        assertEquals("49123 - João Matos, fichas=(15,12,7,11)->13",student1.toText())
        val student2 = Student(889,"Pedro Cunha",listOf(20,18))
        assertEquals("889 - Pedro Cunha, fichas=(20,18)->19",student2.toText())
        val student3 = Student(632,"Luis Guedes",listOf())
        assertEquals("632 - Luis Guedes, fichas=()->0",student3.toText())
    }
}