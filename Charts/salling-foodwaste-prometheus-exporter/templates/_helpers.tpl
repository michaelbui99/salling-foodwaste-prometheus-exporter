{{- define "exporter.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{- define "exporter.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{- define "exporter.labels" -}}
app: {{ include "exporter.fullname" $ | quote }}
chart-name: salling-foodwaste-prometheus-exporter
chart-version: {{ .Chart.Version | quote }}
{{- range $k, $v := .Values.exporter.labels}}
{{ printf "%s: %s" $k ($v | quote) }}
{{- end -}}
{{- end -}}