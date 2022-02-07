////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// File: NGODeleteDocumentExtOutput.java
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


/**
 *
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class NGODeleteDocumentExtOutput {
    private String status;
    private String userDBId;
    private String iSIndexes;
    private String documentSize;
    private String error;
    private FailedDocuments failedDocuments;
    private String option;
    private String loginUserIndex;

    /**
     *
     */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class FailedDocuments {
        private FailedDocument failedDocument;
    }

    /**
     *
     */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class FailedDocument {
        private String statusCode;
        private String documentIndex;
    }
}
