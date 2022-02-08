////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: DmsIndex.java
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
package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;


/**
 * DMS folder index response.
 */
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class DmsIndex {
    private String folderIndex;
    private String docIndex;
}
