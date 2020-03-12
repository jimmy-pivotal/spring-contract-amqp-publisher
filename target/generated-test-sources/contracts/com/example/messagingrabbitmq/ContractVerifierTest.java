package com.example.messagingrabbitmq;

import com.example.messagingrabbitmq.BaseContractTest;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import javax.inject.Inject;
import org.junit.Test;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.messaging.util.ContractVerifierMessagingUtil.headers;

public class ContractVerifierTest extends BaseContractTest {

	@Inject ContractVerifierMessaging contractVerifierMessaging;
	@Inject ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Test
	public void validate_person_message_contract() throws Exception {
		// when:
			onUserIsOnline();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("spring-boot");
			assertThat(response).isNotNull();
			assertThat(response.getHeader("contentType")).isNotNull();
			assertThat(response.getHeader("contentType").toString()).isEqualTo("application/json");
			assertThat(response.getHeader("__TypeId__")).isNotNull();
			assertThat(response.getHeader("__TypeId__").toString()).isEqualTo("com.example.messagingrabbitmq.model.Person");
			assertThat(response.getHeader("amqp_receivedRoutingKey")).isNotNull();
			assertThat(response.getHeader("amqp_receivedRoutingKey").toString()).isEqualTo("foo");
		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
			assertThatJson(parsedJson).field("['firstName']").isEqualTo("John");
			assertThatJson(parsedJson).field("['lastName']").isEqualTo("Smith");
	}

}
