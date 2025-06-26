package at.hugob.annotations;

import org.jetbrains.annotations.NotNull;

import javax.annotation.meta.TypeQualifierDefault;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull
@TypeQualifierDefault({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE_USE, ElementType.LOCAL_VARIABLE, ElementType.TYPE_PARAMETER, ElementType.RECORD_COMPONENT})
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.SOURCE)
public @interface NotNullByDefault {
}
