package io.jexxa.application.domain.valueobject;

import java.util.Objects;

import io.jexxa.application.annotation.ValueObject;

/**
 * This ValueObject include following special cases:
 * <ul>
 *     <li> private field without public getter</li>
 *     <li> private field with null</li>
 * </ul>
 */
@ValueObject
public class SpecialCasesValueObject
{

    public final static SpecialCasesValueObject SPECIAL_CASES_VALUE_OBJECT = new SpecialCasesValueObject(1);

    private final int valueWithoutGetter;
    private final String nullValue = null;

    private SpecialCasesValueObject(int value)
    {
        this.valueWithoutGetter = value;
    }

    public String getNullValue()
    {
        return nullValue;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        SpecialCasesValueObject that = (SpecialCasesValueObject) o;
        return valueWithoutGetter == that.valueWithoutGetter;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(valueWithoutGetter);
    }
}
