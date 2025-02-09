// Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package com.intellij.workspaceModel.storage

import com.intellij.workspaceModel.storage.entities.test.addSampleEntity
import com.intellij.workspaceModel.storage.entities.test.api.SampleEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EntityReferenceTest {
  @Test
  fun basic() {
    val builder = createEmptyBuilder()
    val entity = builder.addSampleEntity("data")
    val reference = entity.createReference<SampleEntity>()
    assertEquals(entity, reference.resolve(builder))

    val snapshot = builder.toSnapshot()
    assertEquals(snapshot.singleSampleEntity(), reference.resolve(snapshot))
    assertNull(reference.resolve(createEmptyBuilder()))

    val newBuilder = createBuilderFrom(snapshot)
    assertEquals(newBuilder.singleSampleEntity(), reference.resolve(newBuilder))
  }

  @Test
  fun equality() {
    val builder = createEmptyBuilder()
    val entity = builder.addSampleEntity("data")
    val entity2 = builder.addSampleEntity("data")
    val reference1 = entity.createReference<SampleEntity>()
    val reference2 = entity.createReference<SampleEntity>()
    val reference3 = entity2.createReference<SampleEntity>()
    assertEquals(reference1, reference2)
    assertNotEquals(reference1, reference3)
  }
}