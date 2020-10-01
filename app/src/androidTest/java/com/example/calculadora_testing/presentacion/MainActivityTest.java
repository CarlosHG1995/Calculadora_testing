package com.example.calculadora_testing.presentacion;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import com.example.calculadora_testing.R;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(JUnitParamsRunner.class)
public class MainActivityTest {

    //Ejercicio crear 1 test de IU con Espresso pag 67

    @Rule public ActivityTestRule <MainActivity> activityRule =
            new ActivityTestRule <>(MainActivity.class);
     /**/@Test public void onClickButtonOneShouldAddOneToOperationsView( ) {
        //Clic en el botón 1
        onView(withId(R.id.bt1)).perform(click());
        //Comprueba si la pantalla de operaciones muestra el 1
        onView(withId(R.id.operations)).check(matches(withText("1")));
    }


    @Test @Parameters(method = "getValidOperandButtonData")
    public void onClickButtonShouldAddExpectedValueToOperationsViews(
            int buttonId, String expectedValue) {
        //Click en el botón
        onView(withId(buttonId)).perform(click());
        //Comprobamos si la pantalla de operaciones muestra el valor esperado
        onView(withId(R.id.operations)).check(matches(withText(expectedValue)));
    }

    private static Object[] getValidOperandButtonData() {
        return new Object[]{
                new Object[]{R.id.bt1, "1"},
                new Object[]{R.id.bt2, "2"},
                new Object[]{R.id.bt3, "3"},
                new Object[]{R.id.bt4, "4"},
                new Object[]{R.id.bt5, "5"},
                new Object[]{R.id.bt6, "6"},
                new Object[]{R.id.bt7, "7"},
                new Object[]{R.id.bt8, "8"},
                new Object[]{R.id.bt9, "9"},
                new Object[]{R.id.bt0, "0"},
                new Object[]{R.id.bt00, "00"},
                new Object[]{R.id.bt_addition, " + "},
                new Object[]{R.id.bt_subtraction, " - "},
                new Object[]{R.id.bt_multiplication, " x "},
                new Object[]{R.id.bt_division, " / "},
                new Object[]{R.id.bt_exponentiation, " ^ "},
                new Object[]{R.id.bt_factorial, " fact ("},
                new Object[]{R.id.bt_square_root, " sqrt ("},
                new Object[]{R.id.bt_dot, "."},
                new Object[]{R.id.bt_parenthesis_start, " ("},
                new Object[]{R.id.bt_parenthesis_end, ") "}
        };}

    //Ejercicio: Crear un ViewAction personalizado pag 69-70

    public static ViewAction setText(final String value) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override public Matcher <View> getConstraints( ) {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }
            @Override public void perform(UiController uiController,View view) {
                ((TextView) view).setText(value);
            }
            @Override public String getDescription() {
                return "cambiar texto";
            }
        };
    }

   /**/ @Test @Parameters(method = "getInValidOperationsData")
    public void Invalid_Operations_ans_is_equal_error(String operations, String result) {
        onView(withId(R.id.operations)).perform(setText(operations));
        onView(allOf(withParent(withId(R.id.result)), withContentDescription("Error")));//.check(matches(withText(result)));
    }

    private static Object[] getInValidOperationsData() {
         return new Object[]{
                 new Object[] {"0^0", "Error"},
                 new Object[] {"0^-10", "Error"},//ojo debo mirarlo
                 new Object[] {"0/0", "Error"},
                 new Object[] {"16/0", "Error"},
                 new Object[] {"-)+", "Error"}
         };
    }

    @Test @Parameters(method = "getValidOperationsData")
    public void onOperationsViewChangedShouldUpdateResultsView(String operations, String result) {
        onView(withId(R.id.operations)).perform(setText(operations));
        onView(allOf(withParent(withId(R.id.result)), isCompletelyDisplayed())).check(matches(withText(result)));
    }

    private static Object[] getValidOperationsData() {
        return new Object[]{
                new Object[]{"2+2", "4"},
                new Object[]{"sqrt(9)", "3"},
                new Object[]{"5/2", "2.5"},
                new Object[] {"3+(4x3)", "15"},
                //otros elementos
                new Object[] {"3^3", "27"},
                new Object[] {"fact(5)", "120"},
                new Object[] {"36/(4x3)", "3"},
                new Object[] {"sqrt(25)+5x(6+2)-8", "37"},
                new Object[] {"-2^(-3)", "-0.125"}

        };
    }

    //
    @Parameters(method = "Remove_Last_Button")
    @Test public void RemoveLastButton_InOperationsView (String entrada, String salida) {
        onView(withId(R.id.operations)).perform(setText(entrada));
        onView(withId(R.id.bt_remove_last)).perform(click());
        onView(withId(R.id.operations)).check(matches(withText(salida)));
    }

    private static Object[] Remove_Last_Button() {
        return new Object[]{
                new Object[]{"2+2", "2 + "},
                new Object[] {"2", ""},
                new Object[] {"2.", "2"},
                new Object[] {"", ""},
                new Object[] {"3x3(3", "3 x 3 ("}
        };
    }

    @Parameters(method = "Clear_Button")
    @Test public void ClearButton_OperationsView(String txt_a_borrar) {
        onView(withId(R.id.operations)).perform(setText(txt_a_borrar));
        onView(withId(R.id.bt_clear)).perform(click());
        onView(withId(R.id.operations)).check(matches(withText("")));
    }

    private static Object[] Clear_Button() {
        return new Object[]{
                new Object[]{"66-2.1"},
                new Object[] {"24"},
                new Object[] {"00."},
                new Object[] {"fact(1)"},
                new Object[] {"7x8(32"}
        };
    }


}
