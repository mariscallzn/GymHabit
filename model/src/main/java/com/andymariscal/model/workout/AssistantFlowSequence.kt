package com.andymariscal.model.workout

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//region AssistantFlowSequence
@JsonClass(generateAdapter = true)
data class AssistantFlowSequence(
    @Json(name = "flow_sequence")
    val flowSequence: List<FlowSequence>
)
//endregion

//region FlowSequence
@JsonClass(generateAdapter = true)
data class FlowSequence(
    val actions: List<Action>,
    val message: String,
    val step: Int
)
//endregion

//region Action
@JsonClass(generateAdapter = true)
data class Action(
    @Json(name = "action_type")
    val actionType: String,
    val value: String
)
//endregion