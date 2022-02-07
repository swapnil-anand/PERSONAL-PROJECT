////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: CreateFolder.java
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

import lombok.Builder;
import lombok.Getter;


/**
 *
 */
@Builder
@Getter
public class CreateFolder {
    private long lookInFolder;
    private String name;
    private String option;
}
