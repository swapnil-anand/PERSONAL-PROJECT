////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: ConfigErrorDetails.java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Sainesh
//
// NSE - Confidential
// Do not use, distribute, or copy without consent of NSE.
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// Copyright (c) 2020 NSE. All rights reserved.
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

package com.nse.listingcompliance.lcdebtannouncementservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("externalized-errors")
@Data
@EnableConfigurationProperties
@NoArgsConstructor
public class ConfigErrorDetails {
    private Map<String, ErrorDetails> errorMapping;

    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class ErrorDetails {
        private String errorCode;
        private String message;
        private int httpStatusCode;   
    }
}