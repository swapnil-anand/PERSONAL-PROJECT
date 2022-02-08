////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: NGODeleteDocumentExtInput.java
//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Author: Sachin
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;


/**
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NGODeleteDocumentExtInput {
    @Value(StringUtils.EMPTY)
    private String userDBId;

    private Documents documents;
    @Value(StringUtils.EMPTY)
    private String cabinetName;

    private String option;

    /**
     *
     */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Documents {
        private DeleteDocument document;

    }

    /**
     *
     */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class DeleteDocument {
        private String referenceFlag;
        private String parentFolderIndex;
        private String documentIndex;
    }
}