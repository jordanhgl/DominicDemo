package com.example.hlint_checks.detectors;

import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;
import com.android.tools.lint.detector.api.XmlContext;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.Collection;

public class TextViewAttrDetector extends ResourceXmlDetector {
    private static final String SCHEME = "http://schemas.android.com/res/android";
    private static final String TEXT_APPEARANCE = "textAppearance";

    @Nullable
    @Override
    public Collection<String> getApplicableElements() {
        return Arrays.asList("TextView");
    }

    @Override
    public void visitElement(@NotNull XmlContext context, @NotNull Element element) {
        super.visitElement(context, element);
        if (!element.hasAttributeNS(SCHEME, TEXT_APPEARANCE)) {
            context.report(ISSUE, context.getLocation(element), ISSUE.getBriefDescription(TextFormat.TEXT));
        }
    }

    public static Issue ISSUE = Issue.create(
            "TextViewAttrDetector_ID",
            "TextViewAttrDetector_Brife",
            "TextViewAttrDetector_explain",
            Category.CORRECTNESS,
            7,
            Severity.WARNING,
            new Implementation(TextViewAttrDetector.class, Scope.RESOURCE_FILE_SCOPE)
    );
}
