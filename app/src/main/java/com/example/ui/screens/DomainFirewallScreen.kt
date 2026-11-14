package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.engine.SsmBackendEngine
import com.example.model.DomainFirewallRow
import com.example.ui.SageCounselUiState
import com.example.ui.theme.ClearCounselContainerLight
import com.example.ui.theme.ClearCounselEmeraldLight
import com.example.ui.theme.JetBrainsMonoFontFamily
import com.example.ui.theme.SafetyPauseContainerLight
import com.example.ui.theme.SafetyPauseCrimsonLight

@Composable
fun DomainFirewallScreen(
    uiState: SageCounselUiState,
    onSetMutateRuleR1: (Boolean) -> Unit,
    onBackToSeeker: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBackToSeeker)

    val clipboardManager = LocalClipboardManager.current
    var selectedSpecTab by rememberSaveable { mutableStateOf(0) }
    var copiedNotice by rememberSaveable { mutableStateOf<String?>(null) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 840.dp)
                .testTag("firewall_lazy_column"),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // 1. Domain Firewall Overview Card
            item {
                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "THE DOMAIN FIREWALL ARCHITECTURE",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = "Eliminating Domain Contamination",
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = "The Super Sage Mode 3.0.2 Genesis Codex (GDCX-SSM-3.0.2-JSON) defines a Socratic consultation inside Appendix C, but exposes internal physics (SankalpaDirective, Clifford Algebras, P-Atlas Polytopes, Rotors, Halahala Singularities) at the top level. This Domain Firewall isolates the math in the backend solver while exposing only clean, human-centered concepts to the Seeker.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                        )
                    }
                }
            }

            // 2. Section 2: Complete 9-Row Domain Firewall Mapping Table
            item {
                DomainFirewallTableCard(rows = SsmBackendEngine.domainFirewallTable)
            }

            // 3. Live Hidden Adapter Inspector (Shows how the current Seeker choices map to internal math)
            item {
                LiveBackendAdapterCard(uiState = uiState)
            }

            // 4. Lean 4 Kernel & Anti-Vacuity Gate (`just vv`)
            item {
                AntiVacuityKernelGateCard(
                    isMutated = uiState.mutateRuleR1ForKernelTest,
                    onSetMutate = onSetMutateRuleR1
                )
            }

            // 5. Section 4: Clean Product Taxonomy & Lean 4 Contract Code Viewer
            item {
                PrismSpecificationViewerCard(
                    selectedTab = selectedSpecTab,
                    onSelectTab = { selectedSpecTab = it },
                    copiedNotice = copiedNotice,
                    onCopyCode = { label, code ->
                        clipboardManager.setText(AnnotatedString(code))
                        copiedNotice = "Copied $label to clipboard"
                    }
                )
            }
        }
    }
}

@Composable
private fun DomainFirewallTableCard(rows: List<DomainFirewallRow>) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "SECTION 2 · DOMAIN FIREWALL TRANSLATION TABLE",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Codex Physics → Clean Product Taxonomy",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface
            )

            rows.forEachIndexed { index, row ->
                if (index > 0) {
                    HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                }
                Column(
                    modifier = Modifier.padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = if (row.isExposedInPrismSurface) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    imageVector = if (row.isExposedInPrismSurface) {
                                        Icons.Default.CheckCircle
                                    } else {
                                        Icons.Default.VisibilityOff
                                    },
                                    contentDescription = null,
                                    modifier = Modifier.size(13.dp),
                                    tint = if (row.isExposedInPrismSurface) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                                Text(
                                    text = row.cleanProductTerm,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (row.isExposedInPrismSurface) {
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                            }
                        }

                        Text(
                            text = row.layerRole,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }

                    Text(
                        text = row.userFacingMeaning,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Isolated SSM 3.0.2 Codex Term: ${row.codexTerm}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LiveBackendAdapterCard(uiState: SageCounselUiState) {
    val trace = uiState.evaluation.internalTrace
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Memory,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary
                )
                Column {
                    Text(
                        text = "SECTION 3 · INTERNAL ADAPTER TELEMETRY (HIDDEN FROM SEEKER)",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Text(
                        text = "Silent Socratic-to-Algebraic Binding",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = "While the Seeker only sees GoalMode, ImpactScale, and ConflictPreference, the backend adapter silently binds the algebraic parameters for the active session:",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AdapterBadge(
                    productLabel = "Goal: ${uiState.selectedGoal.cleanLabel}",
                    backendValue = "Rank P = ${trace.ontologicalRankP} · Manifold ${trace.atlasPolytope}"
                )
                AdapterBadge(
                    productLabel = "Scale: ${uiState.selectedScale.cleanLabel}",
                    backendValue = "Rank n = ${trace.computationalRankN} · Algebra ${trace.cliffordAlgebra}"
                )
                AdapterBadge(
                    productLabel = "Conflict: ${uiState.selectedConflictPreference.cleanLabel}",
                    backendValue = "AUM = ${trace.aumConfig.name}"
                )
                AdapterBadge(
                    productLabel = "BlindSpotCheck",
                    backendValue = "${trace.asuricAudit.name} (H(X) = ${trace.spectralEntropyNat} nat)"
                )
                AdapterBadge(
                    productLabel = "Matched Rule ${trace.matchedRuleId}",
                    backendValue = "${trace.internalState.name} · ${trace.stationaryActionDeltaS}"
                )
            }
        }
    }
}

@Composable
private fun AdapterBadge(productLabel: String, backendValue: String) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = productLabel,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = backendValue,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun AntiVacuityKernelGateCard(
    isMutated: Boolean,
    onSetMutate: (Boolean) -> Unit
) {
    val bannerBg = if (!isMutated) ClearCounselContainerLight else SafetyPauseContainerLight
    val bannerAccent = if (!isMutated) ClearCounselEmeraldLight else SafetyPauseCrimsonLight

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.5.dp, bannerAccent),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = if (!isMutated) Icons.Default.Lock else Icons.Default.BugReport,
                    contentDescription = null,
                    tint = bannerAccent
                )
                Column {
                    Text(
                        text = "PRISM VERIFICATION GATE (just vv · Lean 4 by decide)",
                        style = MaterialTheme.typography.labelSmall,
                        color = bannerAccent
                    )
                    Text(
                        text = if (!isMutated) {
                            "PASS · 6/6 Examples · 5/5 Theorems Verified"
                        } else {
                            "FAIL · Kernel Contradiction in Theorem P1 & EX-03"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        color = bannerAccent
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = bannerBg,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (!isMutated) {
                        "All 3 core acceptance scenarios (Direct Balanced Counsel, Compromise Recovery, Safety Pause) and all 5 formal theorems (P1–P5) pass Lean 4 decision procedures without vacuity."
                    } else {
                        "Mutating Rule R1 to allow unsafe requests into ClearCounsel violates Theorem P1 (p1_unsafeAlwaysPauses) and breaks Acceptance Scenario 3 (EX-03). Lean 4 rejects the build."
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF1F2421),
                    modifier = Modifier.padding(12.dp)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = { onSetMutate(false) },
                    enabled = isMutated,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("canonical_spec_button"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Canonical Spec (PASS)")
                }

                OutlinedButton(
                    onClick = { onSetMutate(true) },
                    enabled = !isMutated,
                    modifier = Modifier
                        .weight(1f)
                        .testTag("mutate_rule_r1_button")
                ) {
                    Text("Mutate Rule R1 (Test Gate)")
                }
            }
        }
    }
}

@Composable
private fun PrismSpecificationViewerCard(
    selectedTab: Int,
    onSelectTab: (Int) -> Unit,
    copiedNotice: String?,
    onCopyCode: (String, String) -> Unit
) {
    val codeText = if (selectedTab == 0) {
        SsmBackendEngine.prismCleanTaxonomyCode
    } else {
        SsmBackendEngine.lean4ContractSnippet
    }
    val codeTitle = if (selectedTab == 0) {
        "SageCounselTaxonomy.prism"
    } else {
        "SageCounselContract.lex.tex"
    }

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Terminal,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "Product Taxonomy & Lean 4 Spec",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                OutlinedButton(
                    onClick = { onCopyCode(codeTitle, codeText) },
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                    modifier = Modifier.testTag("copy_spec_code_button")
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Specification",
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Copy", style = MaterialTheme.typography.labelMedium)
                }
            }

            if (copiedNotice != null) {
                Text(
                    text = copiedNotice,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = selectedTab == 0,
                    onClick = { onSelectTab(0) },
                    label = { Text("Clean Product Taxonomy") },
                    modifier = Modifier.testTag("spec_tab_taxonomy")
                )
                FilterChip(
                    selected = selectedTab == 1,
                    onClick = { onSelectTab(1) },
                    label = { Text("Lean 4 Contract (.lex.tex)") },
                    modifier = Modifier.testTag("spec_tab_lean4")
                )
            }

            Surface(
                shape = RoundedCornerShape(14.dp),
                color = Color(0xFF121A17),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                        .padding(14.dp)
                ) {
                    Text(
                        text = codeText,
                        fontFamily = JetBrainsMonoFontFamily,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFD8F3DC)
                    )
                }
            }
        }
    }
}
