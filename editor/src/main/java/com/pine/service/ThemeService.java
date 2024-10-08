package com.pine.service;

import com.pine.Updatable;
import com.pine.injection.PBean;
import com.pine.injection.PInject;
import com.pine.injection.PostCreation;
import com.pine.repository.SettingsRepository;
import imgui.ImGui;
import imgui.ImGuiStyle;
import imgui.ImVec4;
import imgui.flag.ImGuiCol;

@PBean
public class ThemeService implements Updatable {
    public ImVec4 neutralPalette;
    public ImVec4 palette0;
    public ImVec4 palette1;
    public ImVec4 palette2;
    public ImVec4 palette3;
    public ImVec4 palette4;
    public ImVec4 palette5;
    public ImVec4 palette6;
    public final float[] BACKGROUND_COLOR = new float[]{.0f, .0f, .0f};
    private boolean previousTheme = false;
    private float prevLength;

    @PInject
    public SettingsRepository settingsRepository;

    @Override
    public void tick() {
        if (previousTheme == settingsRepository.isDarkMode && settingsRepository.accentColor.length() == prevLength) {
            return;
        }
        prevLength = settingsRepository.accentColor.length();
        previousTheme = settingsRepository.isDarkMode;

        ImGuiStyle style = ImGui.getStyle();
        ImVec4[] colors = style.getColors();

        if (!settingsRepository.isDarkMode) {
            ImGui.styleColorsLight();
            setLightMode();
        } else {
            ImGui.styleColorsDark();
            setDarkMode();
        }

        colors[ImGuiCol.Text] = palette6;
        colors[ImGuiCol.TextDisabled] = new ImVec4(palette6.x / 2f, palette6.y / 2f, palette6.z / 2f, 1);
        colors[ImGuiCol.WindowBg] = palette1;
        colors[ImGuiCol.ChildBg] = palette1;
        colors[ImGuiCol.PopupBg] = palette1;
        colors[ImGuiCol.Border] = palette3;
        colors[ImGuiCol.BorderShadow] = palette0;
        colors[ImGuiCol.FrameBg] = palette2;
        colors[ImGuiCol.TitleBg] = palette1;
        colors[ImGuiCol.TitleBgActive] = palette1;
        colors[ImGuiCol.TitleBgCollapsed] = palette1;
        colors[ImGuiCol.MenuBarBg] = palette0;
        colors[ImGuiCol.ScrollbarBg] = palette0;
        colors[ImGuiCol.ScrollbarGrab] = palette3;
        colors[ImGuiCol.ScrollbarGrabHovered] = palette4;
        colors[ImGuiCol.ScrollbarGrabActive] = palette2;
        colors[ImGuiCol.SliderGrab] = palette4;
        colors[ImGuiCol.ButtonActive] = palette2;
        colors[ImGuiCol.Separator] = palette5;
        colors[ImGuiCol.SeparatorHovered] = palette6;
        colors[ImGuiCol.SeparatorActive] = palette6;
        colors[ImGuiCol.ResizeGrip] = palette4;
        colors[ImGuiCol.Tab] = palette2;
        colors[ImGuiCol.TabHovered] = palette3;
        colors[ImGuiCol.DockingPreview] = palette4;
        colors[ImGuiCol.DockingEmptyBg] = palette6;
        colors[ImGuiCol.PlotLines] = palette5;
        colors[ImGuiCol.PlotLinesHovered] = palette6;
        colors[ImGuiCol.PlotHistogram] = palette5;
        colors[ImGuiCol.PlotHistogramHovered] = palette6;
        colors[ImGuiCol.DragDropTarget] = palette4;
        colors[ImGuiCol.NavHighlight] = palette3;
        colors[ImGuiCol.NavWindowingHighlight] = palette2;
        colors[ImGuiCol.NavWindowingDimBg] = palette2;
        colors[ImGuiCol.ModalWindowDimBg] = palette2;

        settingsRepository.accent.y = settingsRepository.accentColor.y;
        settingsRepository.accent.z = settingsRepository.accentColor.z;
        settingsRepository.accent.x = settingsRepository.accentColor.x;
        settingsRepository.accent.w = 1;
        settingsRepository.accentU32 = ImGui.getColorU32(settingsRepository.accent);

        colors[ImGuiCol.FrameBgHovered] = settingsRepository.accent;
        colors[ImGuiCol.FrameBgActive] = settingsRepository.accent;
        colors[ImGuiCol.CheckMark] = settingsRepository.accent;
        colors[ImGuiCol.SliderGrabActive] = settingsRepository.accent;
        colors[ImGuiCol.Button] = settingsRepository.accent;
        colors[ImGuiCol.ButtonHovered] = settingsRepository.accent;
        colors[ImGuiCol.Header] = settingsRepository.accent;
        colors[ImGuiCol.HeaderHovered] = settingsRepository.accent;
        colors[ImGuiCol.HeaderActive] = settingsRepository.accent;
        colors[ImGuiCol.ResizeGripHovered] = settingsRepository.accent;
        colors[ImGuiCol.ResizeGripActive] = settingsRepository.accent;
        colors[ImGuiCol.TextSelectedBg] = settingsRepository.accent;

        BACKGROUND_COLOR[0] = colors[ImGuiCol.WindowBg].x;
        BACKGROUND_COLOR[1] = colors[ImGuiCol.WindowBg].y;
        BACKGROUND_COLOR[2] = colors[ImGuiCol.WindowBg].z;

        style.setColors(colors);
    }

    private void setDarkMode() {
        palette0 = new ImVec4(10f / 255f, 10f / 255f, 10f / 255f, 1);
        palette1 = new ImVec4(18f / 255f, 18f / 255f, 18f / 255f, 1);
        palette2 = neutralPalette = new ImVec4(22f / 255f, 22f / 255f, 22f / 255f, 1);
        palette3 = new ImVec4(35f / 255f, 35f / 255f, 35f / 255f, 1);
        palette4 = new ImVec4(65f / 255f, 65f / 255f, 65f / 255f, 1);
        palette5 = new ImVec4(119f / 255f, 119f / 255f, 119f / 255f, 1);
        palette6 = new ImVec4(224f / 255f, 224f / 255f, 224f / 255f, 1);
    }

    private void setLightMode() {
        palette0 = new ImVec4(245f / 255f, 245f / 255f, 245f / 255f, 1); // light gray
        palette1 = new ImVec4(235f / 255f, 235f / 255f, 235f / 255f, 1); // slightly darker gray
        palette2 = neutralPalette = new ImVec4(225f / 255f, 225f / 255f, 225f / 255f, 1); // medium gray
        palette3 = new ImVec4(200f / 255f, 200f / 255f, 200f / 255f, 1); // darker gray
        palette4 = new ImVec4(160f / 255f, 160f / 255f, 160f / 255f, 1); // even darker gray
        palette5 = new ImVec4(120f / 255f, 120f / 255f, 120f / 255f, 1); // dark gray
        palette6 = new ImVec4(10f / 255f, 10f / 255f, 10f / 255f, 1); // dark dark really dark, actually not that dark but dark
    }

    @PostCreation
    public void onInitialize() {
        setDarkMode();
    }
}
