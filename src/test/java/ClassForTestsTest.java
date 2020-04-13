import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.geekbrains.java3.lessonsix.ClassForTests;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ClassForTestsTest {
    private static ClassForTests classForTests = null;
    private int[] inputArray;
    private int[] resultArray;
    private Class<? extends Exception> expectedException;
    private boolean isOneFour;

    public ClassForTestsTest(int[] inputArray, int[] resultArray, Class<? extends Exception> expectedException, boolean isOneFour) {
        this.inputArray = inputArray;
        this.resultArray = resultArray;
        this.expectedException = expectedException;
        this.isOneFour = isOneFour;
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Parameterized.Parameters
    public static Collection inputParameters() {
        return Arrays.asList(new Object[][] {
                {new int[]{1,2,3,4,5,6}, new int[]{5,6}, null, true},
                {new int[]{1,2,3,4,5,6,4}, new int[]{}, null, true},
                {new int[]{1,2,3,5,6}, new int[]{1}, RuntimeException.class, false},
                {new int[]{2,3,5,6,4,7,-150}, new int[]{7,-150}, null, false}

        });
    }

    @Test
    public void cutArrayTest() {
        if (expectedException != null) {
            thrown.expect(expectedException);
        }
        Assert.assertArrayEquals(classForTests.cutArray(inputArray), resultArray);
    }

    @Test
    public void isOneAndFourInArrayTest() {
        Assert.assertEquals(classForTests.checkOeAndFourInArray(inputArray), isOneFour);
    }

    @Before
    public void init() {
        classForTests = new ClassForTests();
    }
    @After
    public void tearDown() {
        classForTests = null;
    }
}
