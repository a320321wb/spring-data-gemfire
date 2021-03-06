/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.springframework.data.gemfire.client.support;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.net.InetSocketAddress;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.data.gemfire.GemfireUtils;

import com.gemstone.gemfire.cache.client.PoolFactory;

/**
 * The FactoryDefaultsPoolAdapterTest class is a test suite of test cases testing the contract and functionality
 * of the {@link FactoryDefaultsPoolAdapter} class.
 *
 * @author John Blum
 * @see org.junit.Rule
 * @see org.junit.Test
 * @see org.junit.rules.ExpectedException
 * @see FactoryDefaultsPoolAdapter
 * @see com.gemstone.gemfire.cache.client.Pool
 * @see com.gemstone.gemfire.cache.client.PoolFactory
 * @since 1.8.0
 */
public class FactoryDefaultsPoolAdapterTest {

	protected static final int DEFAULT_CACHE_SERVER_PORT = GemfireUtils.DEFAULT_CACHE_SERVER_PORT;

	private FactoryDefaultsPoolAdapter poolAdapter = new FactoryDefaultsPoolAdapter() { };

	@Rule
	public ExpectedException exception = ExpectedException.none();

	protected InetSocketAddress newSocketAddress(String host, int port) {
		return new InetSocketAddress(host, port);
	}

	@Test
	public void defaultPoolAdapterConfigurationPropertiesReturnDefaultFactorySettings() {
		assertThat(poolAdapter.getFreeConnectionTimeout(), is(equalTo(PoolFactory.DEFAULT_FREE_CONNECTION_TIMEOUT)));
		assertThat(poolAdapter.getIdleTimeout(), is(equalTo(PoolFactory.DEFAULT_IDLE_TIMEOUT)));
		assertThat(poolAdapter.getLoadConditioningInterval(), is(equalTo(PoolFactory.DEFAULT_LOAD_CONDITIONING_INTERVAL)));
		assertThat(poolAdapter.getMaxConnections(), is(equalTo(PoolFactory.DEFAULT_MAX_CONNECTIONS)));
		assertThat(poolAdapter.getMinConnections(), is(equalTo(PoolFactory.DEFAULT_MIN_CONNECTIONS)));
		assertThat(poolAdapter.getMultiuserAuthentication(), is(equalTo(PoolFactory.DEFAULT_MULTIUSER_AUTHENTICATION)));
		assertThat(poolAdapter.getPRSingleHopEnabled(), is(equalTo(PoolFactory.DEFAULT_PR_SINGLE_HOP_ENABLED)));
		assertThat(poolAdapter.getPingInterval(), is(equalTo(PoolFactory.DEFAULT_PING_INTERVAL)));
		assertThat(poolAdapter.getReadTimeout(), is(equalTo(PoolFactory.DEFAULT_READ_TIMEOUT)));
		assertThat(poolAdapter.getRetryAttempts(), is(equalTo(PoolFactory.DEFAULT_RETRY_ATTEMPTS)));
		assertThat(poolAdapter.getServerGroup(), is(equalTo(PoolFactory.DEFAULT_SERVER_GROUP)));
		assertThat(poolAdapter.getSocketBufferSize(), is(equalTo(PoolFactory.DEFAULT_SOCKET_BUFFER_SIZE)));
		assertThat(poolAdapter.getStatisticInterval(), is(equalTo(PoolFactory.DEFAULT_STATISTIC_INTERVAL)));
		assertThat(poolAdapter.getSubscriptionAckInterval(), is(equalTo(PoolFactory.DEFAULT_SUBSCRIPTION_ACK_INTERVAL)));
		assertThat(poolAdapter.getSubscriptionEnabled(), is(equalTo(PoolFactory.DEFAULT_SUBSCRIPTION_ENABLED)));
		assertThat(poolAdapter.getSubscriptionMessageTrackingTimeout(),
			is(equalTo(PoolFactory.DEFAULT_SUBSCRIPTION_MESSAGE_TRACKING_TIMEOUT)));
		assertThat(poolAdapter.getSubscriptionRedundancy(), is(equalTo(PoolFactory.DEFAULT_SUBSCRIPTION_REDUNDANCY)));
		assertThat(poolAdapter.getThreadLocalConnections(), is(equalTo(PoolFactory.DEFAULT_THREAD_LOCAL_CONNECTIONS)));
	}

	@Test
	public void locatorsEqualsEmptyList() {
		assertThat(poolAdapter.getLocators(), is(equalTo(Collections.<InetSocketAddress>emptyList())));
	}

	@Test
	public void nameEqualsDefault() {
		assertThat(poolAdapter.getName(), is(equalTo(FactoryDefaultsPoolAdapter.DEFAULT_POOL_NAME)));
	}

	@Test
	public void queryServiceIsNull() {
		assertThat(poolAdapter.getQueryService(), is(nullValue()));
	}

	@Test
	public void serversEqualsLocalhostListeningOnDefaultCacheServerPort() {
		assertThat(poolAdapter.getServers(), is(equalTo(Collections.singletonList(
			newSocketAddress("localhost", DEFAULT_CACHE_SERVER_PORT)))));
	}

	@Test
	public void isDestroyedIsUnsupported() {
		exception.expect(UnsupportedOperationException.class);
		exception.expectCause(is(nullValue(Throwable.class)));
		exception.expectMessage(FactoryDefaultsPoolAdapter.NOT_IMPLEMENTED);
		poolAdapter.isDestroyed();
	}

	@Test
	public void getPendingEventCountIsUnsupported() {
		exception.expect(UnsupportedOperationException.class);
		exception.expectCause(is(nullValue(Throwable.class)));
		exception.expectMessage(FactoryDefaultsPoolAdapter.NOT_IMPLEMENTED);
		poolAdapter.getPendingEventCount();
	}

	@Test
	public void destroyedIsUnsupported() {
		exception.expect(UnsupportedOperationException.class);
		exception.expectCause(is(nullValue(Throwable.class)));
		exception.expectMessage(FactoryDefaultsPoolAdapter.NOT_IMPLEMENTED);
		poolAdapter.destroy();
	}

	@Test
	public void destroyedWithKeepAliveIsUnsupported() {
		exception.expect(UnsupportedOperationException.class);
		exception.expectCause(is(nullValue(Throwable.class)));
		exception.expectMessage(FactoryDefaultsPoolAdapter.NOT_IMPLEMENTED);
		poolAdapter.destroy(false);
	}

	@Test
	public void releaseThreadLocalConnectionsIsUnsupported() {
		exception.expect(UnsupportedOperationException.class);
		exception.expectCause(is(nullValue(Throwable.class)));
		exception.expectMessage(FactoryDefaultsPoolAdapter.NOT_IMPLEMENTED);
		poolAdapter.releaseThreadLocalConnection();
	}

}
