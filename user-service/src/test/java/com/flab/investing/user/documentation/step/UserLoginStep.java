package com.flab.investing.user.documentation.step;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

public class UserLoginStep {

    public static RestDocumentationFilter userLoginDocumentationFilter(String identifier) {
        return document(identifier,
                preprocessRequest(modifyUris()
                                .scheme("http")
                                .host("127.0.0.1")
                                .removePort(),
                        prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestFieldsSnippet(),
                responseFieldsSnippet()
        );
    }

    private static RequestFieldsSnippet requestFieldsSnippet() {
        return requestFields(
                fieldWithPath("userId").type(JsonFieldType.STRING).description("아이디"),
                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
        );
    }

    private static ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("응답코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("허용 토큰"),
                fieldWithPath("refreshToken").type(JsonFieldType.STRING).description("재사용 토큰")
        );
    }

}
