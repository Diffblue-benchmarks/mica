/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.dreamlu.mica.context;

import net.dreamlu.mica.config.SpringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

/**
 * 调用链各个服务的上下文信息传递
 *
 * @author L.cm
 */
public class MicaContextHolder {
	private static final ThreadLocal<HttpHeaders> HTTP_HEADERS_HOLDER = new NamedThreadLocal<>("Mica HttpHeaders Holder");

	/**
	 * X-Real-IP x-forwarded-for 请求和转发的ip
	 */
	public static final String[] ALLOW_HEADS = new String[]{
		"X-Real-IP", "x-forwarded-for"
	};

	public static void set(HttpHeaders httpHeaders) {
		HTTP_HEADERS_HOLDER.set(httpHeaders);
	}

	@Nullable
	public static HttpHeaders get() {
		return HTTP_HEADERS_HOLDER.get();
	}

	public static void remove() {
		HTTP_HEADERS_HOLDER.remove();
	}

	/**
	 * 获取链路中传递的账号
	 *
	 * @return 账号信息
	 */
	@Nullable
	public static String getXAccount() {
		MicaHeadersProperties headersProperties = SpringUtils.getBean(MicaHeadersProperties.class);
		if (headersProperties == null) {
			return null;
		}
		HttpHeaders httpHeaders = MicaContextHolder.get();
		if (httpHeaders == null) {
			return null;
		}
		return httpHeaders.getFirst(headersProperties.getAccountHeaderName());
	}
}
