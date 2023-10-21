package com.flab.investing.stock.documentation.step;

import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.request.QueryParametersSnippet;
import org.springframework.restdocs.restassured.RestDocumentationFilter;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

public class StockListStep {

    public static RestDocumentationFilter stockListDocumentationFilter(String identifier) {
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

    private static QueryParametersSnippet requestFieldsSnippet() {
        return queryParameters(
                parameterWithName("size").description("페이지사이즈"),
                parameterWithName("offset").description("시작페이지")
        );
    }

    private static ResponseFieldsSnippet responseFieldsSnippet() {
        return responseFields(
                fieldWithPath("code").type(JsonFieldType.STRING).description("상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("응답 메시지"),
                fieldWithPath("data[]").type(JsonFieldType.ARRAY).description("주식 정보"),
                fieldWithPath("data[].id").type(JsonFieldType.NUMBER).description("주식 아이디"),
                fieldWithPath("data[].code").type(JsonFieldType.STRING).description("주식 코드"),
                fieldWithPath("data[].name").type(JsonFieldType.STRING).description("주식 이름"),
                fieldWithPath("data[].price").type(JsonFieldType.STRING).description("주식 가격")
        );
    }

}
