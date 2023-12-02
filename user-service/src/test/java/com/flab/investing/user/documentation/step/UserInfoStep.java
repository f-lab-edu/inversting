package com.flab.investing.user.documentation.step;

import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

public class UserInfoStep {

    public static RestDocumentationFilter userInfoDocumentationFilter(String identifier) {
        return document(identifier,
                preprocessRequest(modifyUris()
                                .scheme("http")
                                .host("127.0.0.1")
                                .removePort(),
                        prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeadersSnippet(),
                responseFieldsSnippet()
        );
    }

    private static RequestHeadersSnippet requestHeadersSnippet() {
        return requestHeaders(
                headerWithName("accessToken").description("유효토큰")
        );
    }

    private static ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                fieldWithPath("userId").type(JsonFieldType.STRING).description("유저 아이디"),
                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디")
        );
    }

}
