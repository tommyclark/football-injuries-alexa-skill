/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.tommy.footballinjuries.POJO;

import lombok.Data;

import java.util.List;

@Data
public class Footballer {
    private int id;

    private String name;

    private String firstName;

    private String lastName;

    private boolean injured;

    private List<PlayerStatistics> statistics;
}
