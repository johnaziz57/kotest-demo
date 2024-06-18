import BehaviourTest.given
import io.kotest.common.ExperimentalKotest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.Enabled
import io.kotest.core.test.EnabledIf
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe
import io.kotest.property.forAll
import org.example.ComplexLogic
import java.util.Calendar


val enableAdditionOnDemoDay: EnabledIf = { Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY }

@OptIn(ExperimentalKotest::class)
object DescribeTest : DescribeSpec({
    afterTest {
        println("TEST CONCLUDED")
    }
    beforeTest {
        println("INITIATING TEST")
    }
    describe("Demo Test for DescribeSpec").config(enabledIf = enableAdditionOnDemoDay) {
        describe("Testing addition") {
            val subject = ComplexLogic()
            beforeEach {
                println("A special test case")
            }
            it("addition of 1 + 1 is 2") {
                subject.addition(1, 1) shouldBeExactly 2
            }

            it("addition of 8 + 4 is less than 20 and more than 4") {
                subject.addition(8, 4) shouldBeInRange 4..20
            }
        }

        describe("Testing subtraction") {
            val subject = ComplexLogic()
            it("subtraction of 1 - 1 is 0") {
                subject.subtraction(1, 1) shouldBeExactly 0
            }
        }
    }
})

object FunTest : FunSpec({
    val subject = ComplexLogic()
    context("Demo Test for FunTest") {
        context("Testing addition") {
            test("addition of 1+1 is 2") {
                subject.addition(1, 1) shouldBeExactly 2
            }
        }
    }
})

object BehaviourTest : BehaviorSpec({
    context("a broomstick should be able to fly and come back") {
        given("a broomstick") {
            `when`("I take it for a spin") {
                then("I should be able to fly") {
                    "fly " + "away" shouldBe "fly away"
                }
            }
        }
    }
})

fun isEven(number: Int): Boolean {
    return (number % 2) == 0
}

object WithDataTest : DescribeSpec({
    describe("Creating a test with multiple input data") {
        describe("Testing with list") {
            listOf(
                3 to false,
                4 to true,
                8 to true,
                7 to false,
                11 to false
            ).forEach { (number, result) ->
                it("only even numbers are allowed") {
                    isEven(number) shouldBe result
                }
            }
        }

        describe("Testing with WithData") {
            withData(
                3 to false,
                4 to true,
                8 to true,
                7 to false,
                11 to false
            ) { (number, result) ->
                isEven(number) shouldBe result
            }
        }

        describe("Testing with WithData 2") {
            withData(
                nameFn = {"When number is ${it.first} the answer should be ${it.second}"},
                3 to false,
                4 to true,
                8 to true,
                7 to false,
                11 to false
            ) { (number, result) ->
                isEven(number) shouldBe result
            }
        }
    }
})
